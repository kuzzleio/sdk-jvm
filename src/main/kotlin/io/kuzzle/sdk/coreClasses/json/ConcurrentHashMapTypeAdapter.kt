package io.kuzzle.sdk.coreClasses.json

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.internal.LazilyParsedNumber
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import io.kuzzle.sdk.coreClasses.maps.KuzzleMap
import java.io.IOException
import java.util.*

class MapTypeAdapter : TypeAdapter<Map<String?, Any?>?>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, map: Map<String?, Any?>?) {
        if (map == null) {
            out.nullValue()
        } else {
            out.beginObject()
            val iterator = map.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                out.name(entry.key)
                writeObject(out, entry.value)
            }
            out.endObject()
        }
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Map<String?, Any?>? {
        val peek = `in`.peek()
        if (peek == JsonToken.NULL) {
            `in`.nextNull()
            return null
        } else if (peek == JsonToken.BEGIN_OBJECT) {
            val map = KuzzleMap()
            var key: Any
            var value: Any?
            `in`.beginObject()
            while (`in`.hasNext()) {
                key = `in`.nextName()
                value = readObject(`in`)
                if (!map.containsKey(key)) {
                    map.put(key, value)
                } else {
                    throw JsonSyntaxException("duplicate key: $key")
                }
            }
            `in`.endObject()
            return map
        }
        return null
    }

    companion object {
        @Throws(IOException::class)
        fun writeObject(out: JsonWriter, value: Any?) {
            if (value is Number) {
                out.value(value as Number?)
            } else if (value is Boolean) {
                out.value(value as Boolean?)
            } else if (value is String) {
                out.value(value as String?)
            } else if (value is ArrayList<*>) {
                out.beginArray()
                val iterator: Iterator<Any?> = (value as ArrayList<Any?>).iterator()
                while (iterator.hasNext()) {
                    writeObject(out, iterator.next())
                }
                out.endArray()
            } else if (value is Map<*, *>) {
                out.beginObject()
                val iterator: Iterator<Map.Entry<String?, Any?>> = (value as Map<String?, Any?>)
                        .entries
                        .iterator()
                while (iterator.hasNext()) {
                    val e = iterator.next()
                    out.name(e.key)
                    writeObject(out, e.value)
                }
                out.endObject()
            } else if (value is RawJson) {
                out.jsonValue(value.rawJson)
            } else if (value == null) {
                out.nullValue()
            } else {
                out.jsonValue(JsonSerializer.serialize(value))
            }
        }

        @Throws(IOException::class)
        fun readObject(`in`: JsonReader): Any? {
            return when (`in`.peek()) {
                JsonToken.NUMBER -> {
                    val number = `in`.nextString()
                    LazilyParsedNumber(number)
                }
                JsonToken.BOOLEAN -> `in`.nextBoolean()
                JsonToken.STRING -> `in`.nextString()
                JsonToken.NULL -> {
                    `in`.nextNull()
                    null
                }
                JsonToken.BEGIN_ARRAY -> {
                    val array = ArrayList<Any?>()
                    `in`.beginArray()
                    while (`in`.hasNext()) {
                        array.add(readObject(`in`))
                    }
                    `in`.endArray()
                    array
                }
                JsonToken.BEGIN_OBJECT -> {
                    val map = KuzzleMap()
                    `in`.beginObject()
                    while (`in`.hasNext()) {
                        val key = `in`.nextName()
                        val `object` = readObject(`in`)
                        if (`object` != null) {
                            map.put(key, `object`)
                        }
                    }
                    `in`.endObject()
                    map
                }
                JsonToken.END_DOCUMENT, JsonToken.NAME, JsonToken.END_OBJECT, JsonToken.END_ARRAY -> throw IllegalArgumentException()
                else -> throw IllegalArgumentException()
            }
        }
    }
}
