package io.kuzzle.sdk.coreClasses.responses

import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.maps.Serializable


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

  override fun fromMap(map: Map<String?, Any?>?) {
    if (map == null) return
    val kuzzleMap: KuzzleMap? = KuzzleMap?.from(map)
    status = kuzzleMap?.optNumber("status", 0)!!.toInt()
    message = kuzzleMap?.getString("message")
    stack = kuzzleMap?.getString("stack")
    id = kuzzleMap?.getString("id")
  }

  override fun toMap(): Map<String?, Any?> {
    val map = KuzzleMap()
    map["status"] = status
    map["message"] = message!!
    map["stack"] = stack!!
    map["id"] = id!!
    return map
  }
}