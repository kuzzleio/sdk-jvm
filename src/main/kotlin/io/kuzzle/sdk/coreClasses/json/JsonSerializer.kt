package io.kuzzle.sdk.coreClasses.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object JsonSerializer {
    private var gson: Gson? = null
    fun deserialize(rawJson: String?): Map<*, *> {
        return gson!!.fromJson(rawJson, Map::class.java)
    }

    fun serialize(map: Map<String?, Any?>?): String {
        return gson!!.toJson(map, Map::class.java)
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
            .create()
    }
}
