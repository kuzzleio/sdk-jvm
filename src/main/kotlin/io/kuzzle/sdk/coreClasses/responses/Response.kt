package io.kuzzle.sdk.coreClasses.responses

import io.kuzzle.sdk.coreClasses.exceptions.KuzzleExceptionCode
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import io.kuzzle.sdk.coreClasses.maps.Serializable

class Response : Serializable {
    var mapResponse: Map<String?, Any?>? = null
        private set

    var room: String? = null

    /**
     * Response payload (depends on the executed API action)
     */
    var result: Any? = null

    /**
     * Error object (null if the request finished successfully)
     */
    var error: ErrorResponse? = null

    /**
     * Request unique identifier.
     */
    var requestId: String? = null

    /**
     * Response status, following HTTP status codes
     */
    var status = 0

    /**
     * Executed Kuzzle API controller
     */
    var controller: String? = null

    /**
     * Executed Kuzzle API controller's action.
     */
    var action: String? = null

    /**
     * Impacted data index.
     */
    var index: String? = null

    /**
     * Impacted data collection.
     */
    var collection: String? = null

    /**
     * Volatile data.
     */
    var Volatile: Map<String?, Any?>? = null
    // The following properties are specific to real-time notifications
    /**
     * Network protocol at the origin of the real-time notification.
     */
    var protocol: String? = null

    /**
     * Document scope ("in" or "out")
     */
    var scope: String? = null

    /**
     * Document state
     */
    var state: String? = null

    /**
     * Notification timestamp (UTC)
     */
    var timestamp: Long? = null

    /**
     * Notification type
     */
    var type: String? = null

    override fun fromMap(map: Map<String?, Any?>?) {
        if (map == null) return

        mapResponse = map

        val kuzzleMap = KuzzleMap(map)
        room = kuzzleMap.getString("room")
        result = kuzzleMap.get("result")
        error = null
        if (kuzzleMap.isMap("error")) {
            error = ErrorResponse()
            error!!.fromMap(kuzzleMap.getMap("error"))
        }
        requestId = kuzzleMap.getString("requestId")
        if (requestId == null) {
            throw Exception(KuzzleExceptionCode.MISSING_REQUESTID.message)
        }
        status = (kuzzleMap.optNumber("status", 0) as com.google.gson.internal.LazilyParsedNumber).toInt()
        controller = kuzzleMap.getString("controller")
        action = kuzzleMap.getString("action")
        index = kuzzleMap.getString("index")
        collection = kuzzleMap.getString("collection")
        Volatile = kuzzleMap.optMap("volatile", HashMap())
        protocol = kuzzleMap.getString("protocol")
        scope = kuzzleMap.getString("scope")
        state = kuzzleMap.getString("state")
        timestamp = when (kuzzleMap.getNumber("timestamp")) {
            null -> null
            else -> (kuzzleMap.getNumber("timestamp") as com.google.gson.internal.LazilyParsedNumber).toLong()
        }
        type = kuzzleMap.getString("type")
    }

    override fun toMap(): Map<String?, Any?> {
        val map = HashMap<String?, Any?>()

        room?.let { map.put("room", it) }
        result?.let { map.put("result", it) }
        error?.let { map.put("error", it) }
        requestId?.let { map.put("requestId", it) }
        map.put("status", status)
        controller?.let { map.put("controller", it) }
        action?.let { map.put("action", it) }
        index?.let { map.put("index", it) }
        collection?.let { map.put("collection", it) }
        Volatile?.let { map.put("volatile", it) }
        protocol?.let { map.put("protocol", it) }
        scope?.let { map.put("scope", it) }
        map.put("status", status)
        timestamp?.let { map.put("timestamp", it) }
        type?.let { map.put("type", it) }

        return map
    }

    override fun toString(): String {
        return mapResponse.toString()
    }
}
