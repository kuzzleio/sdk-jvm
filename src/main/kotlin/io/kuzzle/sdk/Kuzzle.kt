package io.kuzzle.sdk

import io.kuzzle.sdk.coreClasses.exceptions.KuzzleException
import io.kuzzle.sdk.coreClasses.exceptions.KuzzleExceptionCode
import io.kuzzle.sdk.coreClasses.exceptions.NotConnectedException
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.protocol.AbstractProtocol
import io.kuzzle.sdk.protocol.ProtocolState
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

class Kuzzle(private val protocol: AbstractProtocol) {
  private val queries: HashMap<String, CompletableFuture<Object>> = HashMap()
  private val instanceId: String
  private val version: String = "3"
  private val sdkName: String = "java@$version"
  var authenticationToken: String? = null

  private fun onMessageReceived(message: String) {
    val json = JsonSerializer.deserialize(message)
    queries[json["requestId"]]?.complete(message as Object)
    queries.remove(json["requestId"])
  }

  init {
    // @TODO Create enums for events
    protocol.addListener("messageReceived", ::onMessageReceived)
    instanceId = UUID.randomUUID().toString()
  }

  fun connect() {
    protocol.connect()
  }

  fun query(query: ConcurrentHashMap<String?, Any?>): CompletableFuture<Object> {
    if (protocol.state == ProtocolState.CLOSE) {
      throw NotConnectedException()
    }

    val futureRes: CompletableFuture<Object> = CompletableFuture()
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
      throw KuzzleException(KuzzleExceptionCode.WRONG_VOLATILE_TYPE.toString())
    }

    queryMap.getMap("volatile")!!["sdkInstanceId"] = instanceId

    queryMap.getMap("volatile")!!["sdkName"] = sdkName

    protocol.send(query)

    return futureRes
  }
}