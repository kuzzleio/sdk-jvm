package io.kuzzle.sdk.protocolTest.coreClasses.responses

import io.kuzzle.sdk.protocolTest.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.protocolTest.coreClasses.maps.Serializable
import java.util.concurrent.ConcurrentHashMap

class ErrorResponse : Serializable {
  /**
   * Response status, following HTTP status codes.
   */
  var status = 0

  /**
   * Error message
   */
  var message: String? = null

  /**
   * Error ID
   */
  var id: String? = null

  /**
   * Error stack
   */
  var stack: String? = null

  override fun fromMap(map: ConcurrentHashMap<String?, Any?>?) {
    if (map == null) return
    val kuzzleMap: KuzzleMap? = KuzzleMap?.from(map)
    status = kuzzleMap?.optNumber("status", 0) as Int
    message = kuzzleMap?.getString("message")
    stack = kuzzleMap?.getString("stack")
    id = kuzzleMap?.getString("id")
  }

  override fun toMap(): ConcurrentHashMap<String?, Any?> {
    val map: ConcurrentHashMap<String?, Any?> = KuzzleMap()
    map["status"] = status
    map["message"] = message!!
    map["stack"] = stack!!
    map["id"] = id!!
    return map
  }
}