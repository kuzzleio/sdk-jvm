package io.kuzzle.sdk.coreClasses.maps

interface Serializable {
    @Throws(Exception::class)
    fun fromMap(map: Map<String?, Any?>?)

    @Throws(Exception::class)
    fun toMap(): Map<String?, Any?>?
}
