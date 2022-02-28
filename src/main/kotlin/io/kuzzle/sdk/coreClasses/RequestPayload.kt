package io.kuzzle.sdk.coreClasses

data class RequestPayload(
    var controller: String? = null,
    var action: String? = null,
    var index: String? = null,
    var collection: String? = null,
    var _id: String? = null,
    var jwt: String? = null,
    var volatile: Any? = null,
    var body: Any? = null,
    var requestId: String? = null,
    var other: Map<String?, Any?>? = null
) {
    fun toMap(): Map<String?, Any?> {
        val map = HashMap<String?, Any?>()

        if (controller != null) {
            map["controller"] = controller
        }

        if (action != null) {
            map["action"] = action
        }

        if (index != null) {
            map["index"] = index
        }

        if (collection != null) {
            map["collection"] = collection
        }

        if (_id != null) {
            map["_id"] = _id
        }

        if (jwt != null) {
            map["jwt"] = jwt
        }

        if (volatile != null) {
            map["volatile"] = volatile
        }

        if (body != null) {
            map["body"] = body
        }

        if (requestId != null) {
            map["requestId"] = requestId
        }

        if (other != null) {
            map.putAll(other!!)
        }

        return map
    }
}