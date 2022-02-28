package io.kuzzle.sdk.coreClasses.json

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import io.kuzzle.sdk.coreClasses.RequestPayload
import java.io.IOException

class RequestTypeAdapter : TypeAdapter<RequestPayload>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: RequestPayload?) {
        if (value == null) {
            out.nullValue()
            return
        }

        out.beginObject()

        if (value.controller != null) {
            out.name("controller")
            out.value(value.controller)
        }

        if (value.action != null) {
            out.name("action")
            out.value(value.action)
        }

        if (value.index != null) {
            out.name("index")
            out.value(value.index)
        }

        if (value.collection != null) {
            out.name("collection")
            out.value(value.collection)
        }

        if (value._id != null) {
            out.name("_id")
            out.value(value._id)
        }

        if (value.jwt != null) {
            out.name("jwt")
            out.value(value.jwt)
        }

        if (value.body != null) {
            out.name("body")
            out.jsonValue(JsonSerializer.serialize(value.body!!))
        }

        if (value.volatile != null) {
            out.name("volatile")
            out.jsonValue(JsonSerializer.serialize(value.volatile!!))
        }

        if (value.requestId != null) {
            out.name("requestId")
            out.value(value.requestId)
        }

        if (value.other != null) {
            out.name("action")
            MapTypeAdapter.writeObject(out, value.other)
        }
        out.endObject()
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): RequestPayload? {
        val peek = `in`.peek()
        if (peek != JsonToken.BEGIN_OBJECT) {
            `in`.nextNull()
            return null
        }
        val request = RequestPayload()

        val other = HashMap<String?, Any?>()

        `in`.beginObject()
        while (`in`.hasNext()) {
            val key = `in`.nextName()
            when (key) {
                "controller" -> {
                    request.controller = readType(`in`, JsonToken.STRING)
                }
                "action" -> {
                    request.action = readType(`in`, JsonToken.STRING)
                }
                "index" -> {
                    request.index = readType(`in`, JsonToken.STRING)
                }
                "collection" -> {
                    request.collection = readType(`in`, JsonToken.STRING)
                }
                "_id" -> {
                    request._id = readType(`in`, JsonToken.STRING)
                }
                "jwt" -> {
                    request.jwt = readType(`in`, JsonToken.STRING)
                }
                "volatile" -> {
                    request.volatile = readType<Map<String?, Any?>>(`in`, JsonToken.BEGIN_OBJECT)
                }
                "body" -> {
                    request.body = readType<Map<String?, Any?>>(`in`, JsonToken.BEGIN_OBJECT)
                }
                "requestId" -> {
                    request.requestId = readType(`in`, JsonToken.STRING)
                }
                else -> {
                    other.put(key, MapTypeAdapter.readObject(`in`))
                }
            }
        }
        `in`.endObject()

        if (other.size > 0) {
            request.other = other
        }

        return request
    }

    fun <T> readType(`in`: JsonReader, type: JsonToken): T? {
        val token = `in`.peek()
        if (type != token) {
            return null
        }

        return MapTypeAdapter.readObject(`in`) as T
    }
}