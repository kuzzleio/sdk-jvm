package io.kuzzle.sdk

import io.kuzzle.sdk.controllers.AuthController
import io.kuzzle.sdk.controllers.BulkController
import io.kuzzle.sdk.controllers.CollectionController
import io.kuzzle.sdk.controllers.DocumentController
import io.kuzzle.sdk.controllers.IndexController
import io.kuzzle.sdk.controllers.RealtimeController
import io.kuzzle.sdk.controllers.ServerController
import io.kuzzle.sdk.coreClasses.RequestPayload
import io.kuzzle.sdk.coreClasses.exceptions.ApiErrorException
import io.kuzzle.sdk.coreClasses.exceptions.InvalidJSON
import io.kuzzle.sdk.coreClasses.exceptions.KuzzleExceptionCode
import io.kuzzle.sdk.coreClasses.exceptions.NotConnectedException
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.responses.Response
import io.kuzzle.sdk.events.*
import io.kuzzle.sdk.protocol.AbstractProtocol
import io.kuzzle.sdk.protocol.ProtocolState
import java.util.UUID
import java.util.concurrent.CompletableFuture
import kotlin.collections.HashMap

open class Kuzzle {
    val protocol: AbstractProtocol
    val autoResubscribe: Boolean
    private val queries: HashMap<String, CompletableFuture<Response>> = HashMap()
    val instanceId: String
    private val version: String = "1"
    private val sdkName: String = "jvm@$version"
    var authenticationToken: String? = null
    val realtimeController: RealtimeController
    val documentController: DocumentController
    val indexController: IndexController
    val authController: AuthController
    val serverController: ServerController
    val collectionController: CollectionController
    val bulkController: BulkController

    @JvmOverloads
    constructor(protocol: AbstractProtocol, autoResubscribe: Boolean = true) {
        this.protocol = protocol
        this.autoResubscribe = autoResubscribe
        instanceId = UUID.randomUUID().toString()
        realtimeController = RealtimeController(this)
        documentController = DocumentController(this)
        indexController = IndexController(this)
        authController = AuthController(this)
        serverController = ServerController(this)
        collectionController = CollectionController(this)
        bulkController = BulkController(this)
        // @TODO Create enums for events
        protocol.addListener(::onMessageReceived)
        protocol.addListener(::onNetworkStateChange)
        protocol.addListener(::onRequestError)
    }

    private fun onRequestError(event: RequestErrorEvent) {
        if (event.requestId != null && queries[event.requestId!!] != null) {
            queries[event.requestId!!]?.completeExceptionally(event.exception)
            queries.remove(event.requestId!!)
        } else {
            protocol.trigger(UnhandledExceptionEvent(event.exception))
        }
    }

    private fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message
        var jsonObject: Map<String?, Any?>
        val eventRequestId = event.payload["requestId"] as String?
        try {
            jsonObject = JsonSerializer.deserialize(message) as Map<String?, Any?>
        } catch (e: Exception) {
            if (eventRequestId != null) {
                queries[eventRequestId]?.completeExceptionally(InvalidJSON(event.message ?: "null"))
                queries.remove(eventRequestId)
            } else {
                protocol.trigger(UnhandledExceptionEvent(InvalidJSON(event.message ?: "null")))
            }
            return
        }
        val response = Response()

        // If the message is empty, we take the requestId of the event,
        // to avoid error in fromMap function.
        if (! jsonObject.containsKey("requestId") && eventRequestId != null) {
            response.result = jsonObject
            response.requestId = eventRequestId
            response.status = event.status
            if (event.headers != null) {
                response.headers = event.headers as Map<String?, Any?>
                if (response.headers!!.containsKey("X-Kuzzle-Volatile")) {
                    response.Volatile = response.headers!!["X-Kuzzle-Volatile"] as Map<String?, Any?>?
                }
            }

            if (response.Volatile == null) {
                response.Volatile = event.payload["volatile"] as Map<String?, Any?>?
            }
            response.controller = event.payload["controller"] as String?
            response.action = event.payload["action"] as String?
            response.index = event.payload["index"] as String?
            response.collection = event.payload["collection"] as String?
        } else {
            if (! jsonObject.containsKey("headers") && event.headers != null) {
                jsonObject = jsonObject.plus("headers" to event.headers)
            }
            response.apply {
                fromMap(jsonObject)
            }
        }

        val requestId = eventRequestId ?: response.room ?: response.requestId

        if (response.error?.id == "security.token.expired") {
            protocol.trigger(TokenExpiredEvent())
        }

        if (queries.isEmpty() || requestId == null || queries[requestId] == null) {
            if (response.error != null) {
                protocol.trigger(UnhandledExceptionEvent(ApiErrorException(response)))
                return
            }
            protocol.trigger(RoomMessageEvent(response))
            return
        }

        if (response.error != null) {
            queries[requestId]?.completeExceptionally(ApiErrorException(response))
        } else {
            queries[requestId]?.complete(response)
        }

        queries.remove(requestId)
    }

    private fun onNetworkStateChange(event: NetworkStateChangeEvent) {
        if (event.state == ProtocolState.OPEN && autoResubscribe) {
            realtimeController.renewSubscriptions()
        }
    }

    fun connect() {
        protocol.connect()
    }

    fun disconnect() {
        protocol.disconnect()
    }

    fun query(query: RequestPayload): CompletableFuture<Response> {
        return query(query.toMap())
    }

    fun query(query: Any): CompletableFuture<Response> {
        return query(JsonSerializer.serialize(query))
    }

    fun query(query: String): CompletableFuture<Response> {
        val queryMap = JsonSerializer.deserialize(query) as Map<String?, Any?>
        return query(queryMap)
    }

    fun query(query: Map<String?, Any?>): CompletableFuture<Response> {
        if (protocol.state == ProtocolState.CLOSE) {
            throw NotConnectedException()
        }

        val futureRes: CompletableFuture<Response> = CompletableFuture()
        val requestId = UUID.randomUUID().toString()
        val queryMap = KuzzleMap.from(query)

        if (query.containsKey("waitForRefresh")) {
            if (query["waitForRefresh"] == true) {
                queryMap["refresh"] = "wait_for"
            }
            queryMap.remove("waitForRefresh")
        }

        if (authenticationToken != null) {
            queryMap["jwt"] = authenticationToken
        }

        queries[requestId] = futureRes
        queryMap["requestId"] = requestId

        if (!queryMap.containsKey("volatile") || queryMap.isNull("volatile")) {
            queryMap["volatile"] = KuzzleMap()
        } else if (!queryMap.isMap("volatile")) {
            throw Exception(KuzzleExceptionCode.WRONG_VOLATILE_TYPE.toString())
        }

        queryMap.getMap("volatile")!!["sdkInstanceId"] = instanceId

        queryMap.getMap("volatile")!!["sdkName"] = sdkName

        protocol.send(queryMap)

        return futureRes
    }
}
