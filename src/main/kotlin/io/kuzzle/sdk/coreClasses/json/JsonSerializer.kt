package io.kuzzle.sdk.coreClasses.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.concurrent.ConcurrentHashMap

object JsonSerializer {
    private var gson: Gson? = null
    fun deserialize(rawJson: String?): ConcurrentHashMap<*, *> {
        return gson!!.fromJson(rawJson, ConcurrentHashMap::class.java)
    }

    fun serialize(map: ConcurrentHashMap<String?, Any?>?): String {
        return gson!!.toJson(map, ConcurrentHashMap::class.java)
    }

    init {
        gson = GsonBuilder()
            .disableHtmlEscaping()
            .disableInnerClassSerialization()
            .serializeNulls()
            .registerTypeAdapter(
                ConcurrentHashMap::class.java,
                ConcurrentHashMapTypeAdapter()
            )
            .create()
    }
}
