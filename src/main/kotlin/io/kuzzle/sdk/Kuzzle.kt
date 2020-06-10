package io.kuzzle.sdk

import io.kuzzle.sdk.controllers.RealtimeController
import io.kuzzle.sdk.coreClasses.exceptions.ApiErrorException
import io.kuzzle.sdk.coreClasses.exceptions.KuzzleExceptionCode
import io.kuzzle.sdk.coreClasses.exceptions.NotConnectedException
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.responses.Response
import io.kuzzle.sdk.protocol.AbstractProtocol
import io.kuzzle.sdk.protocol.ProtocolState
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

class Kuzzle(val protocol: AbstractProtocol) {
  private val queries: HashMap<String, CompletableFuture<Response>> = HashMap()
  val instanceId: String
  private val version: String = "1"
  private val sdkName: String = "jvm@$version"
  private var authenticationToken: String? = null
  val realtimeController: RealtimeController

  private fun onMessageReceived(message: String?) {
    val response = Response().apply {
      fromMap(JsonSerializer.deserialize(message) as ConcurrentHashMap<String?, Any?>)
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

    val error = JsonSerializer.deserialize(response.error.toString())
    if (error["id"] == null
        || error["id"]!! != "security.token.expired") {
      queries[response.requestId]?.completeExceptionally(ApiErrorException(response))
      queries.remove(response.requestId)
      return
    }

    protocol.trigger("tokenExpired")
  }

  init {
    // @TODO Create enums for events
    protocol.addListener("messageReceived", ::onMessageReceived)
    instanceId = UUID.randomUUID().toString()
    realtimeController = RealtimeController(this)
  }

  fun connect() {
    protocol.connect()
  }

  fun query(query: ConcurrentHashMap<String?, Any?>): CompletableFuture<Response> {
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
    query["requestId"] = requestId

    if (!queryMap.containsKey("volatile") || queryMap.isNull("volatile")) {
      queryMap["volatile"] = KuzzleMap()
    } else if (!queryMap.isMap("volatile")) {
      throw Exception(KuzzleExceptionCode.WRONG_VOLATILE_TYPE.toString())
    }

    queryMap.getMap("volatile")!!["sdkInstanceId"] = instanceId

    queryMap.getMap("volatile")!!["sdkName"] = sdkName

    protocol.send(query)

    return futureRes
  }
}