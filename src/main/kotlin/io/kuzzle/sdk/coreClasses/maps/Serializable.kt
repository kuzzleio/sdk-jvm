package io.kuzzle.sdk.coreClasses.maps

import java.util.concurrent.ConcurrentHashMap

interface Serializable {
    @Throws(Exception::class)
    fun fromMap(map: ConcurrentHashMap<String?, Any?>?)

    @Throws(Exception::class)
    fun toMap(): ConcurrentHashMap<String?, Any?>?
}
