package io.kuzzle.sdk

import io.kuzzle.sdk.controllers.AuthController
import io.kuzzle.sdk.controllers.BulkController
import io.kuzzle.sdk.controllers.CollectionController
import io.kuzzle.sdk.controllers.DocumentController
import io.kuzzle.sdk.controllers.IndexController
import io.kuzzle.sdk.controllers.RealtimeController
import io.kuzzle.sdk.controllers.ServerController
import io.kuzzle.sdk.coreClasses.exceptions.ApiErrorException
import io.kuzzle.sdk.coreClasses.exceptions.KuzzleExceptionCode
import io.kuzzle.sdk.coreClasses.exceptions.NotConnectedException
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.responses.Response
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
        protocol.addListener("messageReceived", ::onMessageReceived)
        protocol.addListener("networkStateChange", ::onNetworkStateChange)
    }

    private fun onMessageReceived(message: String?) {
        val response = Response().apply {
            fromMap(JsonSerializer.deserialize(message) as Map<String?, Any?>)
        }

        if (queries.size == 0 || (queries.size != 0 && (response.room == null || queries[response.room!!] == null))) {
            protocol.trigger("unhandledResponse", message)
            return
        }

        if (response.error == null) {
            queries[response.requestId]?.complete(response)
            queries.remove(response.requestId)
            return
        }

        if (response.error?.id == null ||
            response.error?.id != "security.token.expired"
        ) {
            queries[response.requestId]?.completeExceptionally(ApiErrorException(response))
            queries.remove(response.requestId)
            return
        }

        queries[response.requestId]?.completeExceptionally(ApiErrorException(response))
        protocol.trigger("tokenExpired")
    }

    private fun onNetworkStateChange(state: String?) {
        if (state == ProtocolState.OPEN.toString() && autoResubscribe) {
            realtimeController.renewSubscriptions()
        }
    }

    fun connect() {
        protocol.connect()
    }

    fun disconnect() {
        protocol.disconnect()
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
