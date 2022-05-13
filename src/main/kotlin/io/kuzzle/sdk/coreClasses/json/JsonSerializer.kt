package io.kuzzle.sdk.coreClasses.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.kuzzle.sdk.coreClasses.RequestPayload
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap

object JsonSerializer {
    private var gson: Gson? = null
    fun deserialize(rawJson: String?): Map<*, *> {
        return if (rawJson == null || rawJson.isBlank()) {
            KuzzleMap()
        } else {
            gson!!.fromJson(rawJson, Map::class.java)
        }
    }

    fun serialize(obj: Any): String {
        if (obj is Map<*, *>) {
            return gson!!.toJson(obj, Map::class.java)
        }
        if (obj is RawJson) {
            return obj.rawJson
        }
        return gson!!.toJson(obj)
    }

    init {
        gson = GsonBuilder()
            .disableHtmlEscaping()
            .disableInnerClassSerialization()
            .serializeNulls()
            .registerTypeAdapter(
                Map::class.java,
                MapTypeAdapter()
            )
            .registerTypeAdapter(
                RequestPayload::class.java,
                RequestTypeAdapter()
            )
            .create()
    }
}
