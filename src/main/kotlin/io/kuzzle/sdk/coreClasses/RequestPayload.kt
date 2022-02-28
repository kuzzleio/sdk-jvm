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
            map.put("controller", controller)
        }

        if (action != null) {
            map.put("controller", action)
        }

        if (index != null) {
            map.put("controller", index)
        }

        if (collection != null) {
            map.put("controller", collection)
        }

        if (_id != null) {
            map.put("controller", _id)
        }

        if (jwt != null) {
            map.put("controller", jwt)
        }

        if (volatile != null) {
            map.put("controller", volatile)
        }

        if (body != null) {
            map.put("controller", body)
        }

        if (requestId != null) {
            map.put("controller", requestId)
        }

        if (other != null) {
            map.putAll(other!!)
        }

        return map
    }
}