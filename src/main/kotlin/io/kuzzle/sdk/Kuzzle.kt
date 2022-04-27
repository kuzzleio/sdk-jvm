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
        }
    }

    private fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message
        val jsonObject: Map<String?, Any?>
        try {
            jsonObject = JsonSerializer.deserialize(message) as Map<String?, Any?>
        } catch (e: Exception) {
            if (event.requestId != null) {
                queries[event.requestId]?.completeExceptionally(InvalidJSON(event.message ?: "null"))
                queries.remove(event.requestId)
            } else {
                protocol.trigger(UnhandledResponseEvent(message))
            }
            return
        }
        val response = Response().apply {
            fromMap(jsonObject)
        }

        val requestId = event.requestId ?: response.room ?: response.requestId

        if (queries.size == 0 || requestId == null || queries[requestId] == null) {
            protocol.trigger(UnhandledResponseEvent(message))
            return
        }

        if (response.error == null) {
            queries[requestId]?.complete(response)
            queries.remove(requestId)
            return
        }

        if (response.error?.id == null ||
            response.error?.id != "security.token.expired"
        ) {
            queries[requestId]?.completeExceptionally(ApiErrorException(response))
            queries.remove(requestId)
            return
        }

        queries[requestId]?.completeExceptionally(ApiErrorException(response))
        queries.remove(requestId)
        protocol.trigger(TokenExpiredEvent())
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
