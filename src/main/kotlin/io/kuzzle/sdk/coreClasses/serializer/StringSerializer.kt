package io.kuzzle.sdk.coreClasses.serializer

import io.kuzzle.sdk.coreClasses.json.JsonSerializer

object StringSerializer {
    fun serialize(obj: Any): String {
        if (obj == null) {
            return ""
        }
        if (obj is Map<*, *> || obj is List<*>) {
            return JsonSerializer.serialize(obj)
        }
        return obj.toString()
    }
}