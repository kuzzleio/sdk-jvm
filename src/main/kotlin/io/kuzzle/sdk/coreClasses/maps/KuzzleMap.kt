package io.kuzzle.sdk.coreClasses.maps

import java.util.concurrent.ConcurrentHashMap

/**
 * CustomMap is a Class that extends ConcurrentHashMap to be ThreadSafe and that
 * has the purpose of giving a wrapper on top of ConcurrentHashMap to easily
 * manipulate them.
 */
class KuzzleMap : ConcurrentHashMap<String?, Any?> {
    /**
     * Create a new instance of CustomMap
     */
    constructor() : super() {}

    /**
     * Create a new instance of CustomMap from a ConcurrentHashMap<String></String>,
     * Object>.
     *
     * @param map
     * ConcurrentHashMap<String></String>, Object> representing JSON.
     */
    constructor(map: ConcurrentHashMap<String?, Any?>) : super() {
        val it = map.entries.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            this[entry.key] = entry.value
        }
    }

    fun put(s: String, o: Any?): KuzzleMap? {
        if (o != null) {
            super.put(s, o)
        }
        return this
    }

    override operator fun get(key: String?): Any? {
        val value = super.get(key)
        return if (value is Null) {
            null
        } else value
    }

    /**
     * Check whether the key value is null or not.
     *
     * @param key
     * a String representing the key.
     * @return true if the value is null.
     */
    fun isNull(key: String): Boolean {
        return super.get(key) is Null
    }

    /**
     * Check whether the key value is a String or not.
     *
     * @param key
     * a String representing the key.
     * @return true if the key is a String.
     */
    fun isString(key: String): Boolean {
        return super.get(key) is String
    }

    /**
     * Check whether the key value is a Boolean or not.
     *
     * @param key
     * a String representing the key.
     * @return true if the key is a Boolean.
     */
    fun isBoolean(key: String): Boolean {
        return super.get(key) is Boolean
    }

    /**
     * Check whether the key value is a Number or not.
     *
     * @param key
     * a String representing the key.
     * @return true if the key is a Number.
     */
    fun isNumber(key: String): Boolean {
        return super.get(key) is Number
    }

    /**
     * Check whether the key value is an ArrayList or not.
     *
     * @param key
     * a String representing the key.
     * @return true if the key is an ArrayList.
     */
    fun isArrayList(key: String): Boolean {
        return super.get(key) is ArrayList<*>
    }

    /**
     * Check whether the key value is a ConcurrentHashMap or not.
     *
     * @param key
     * a String representing the key.
     * @return true if the key is a ConcurrentHashMap.
     */
    fun isMap(key: String): Boolean {
        return super.get(key) is ConcurrentHashMap<*, *>
    }

    /**
     * Return the specified key value or null if the value is not a String.
     *
     * @param key
     * a String representing the key.
     * @return The String at the key or null
     */
    fun getString(key: String): String? {
        return if (isString(key)) super.get(key) as String else null
    }

    /**
     * Return the specified key value or null if the value is not a Boolean.
     *
     * @param key
     * a String representing the key.
     * @return The Boolean at the key or null
     */
    fun getBoolean(key: String): Boolean? {
        return if (isBoolean(key)) super.get(key) as Boolean? else null
    }

    /**
     * Return the specified key value or null if the value is not a Number.
     *
     * @param key
     * a String representing the key.
     * @return The Number at the key or null
     */
    fun getNumber(key: String): Number? {
        return if (isNumber(key)) super.get(key) as Number? else null
    }

    /**
     * Return the specified key value or null if the value is not an ArrayList.
     *
     * @param key
     * a String representing the key.
     * @return The ArrayList at the key or null
     */
    fun getArrayList(key: String): ArrayList<*>? {
        return if (isArrayList(key)) super.get(key) as ArrayList<*>? else null
    }

    /**
     * Return the specified key value or null if the value is not a
     * ConcurrentHashMap.
     *
     * @param key
     * a String representing the key.
     * @return The ConcurrentHashMap at the key or null
     */
    fun getMap(key: String): KuzzleMap? {
        return if (isMap(key)) from(super.get(key) as ConcurrentHashMap<String?, Any?>) else null
    }

    /**
     * Return the specified key value or the def value if the value is nul or not
     * a String.
     *
     * @param key
     * a String representing the key.
     * @return The String at the key or def value
     */
    fun optString(key: String, def: String): String {
        return if (isString(key)) super.get(key) as String else def
    }

    /**
     * Return the specified key value or the def value if the value is nul or not
     * a Boolean.
     *
     * @param key
     * a String representing the key.
     * @return The Boolean at the key or def value
     */
    fun optBoolean(key: String, def: Boolean?): Boolean? {
        return if (isBoolean(key)) super.get(key) as Boolean? else def
    }

    /**
     * Return the specified key value or the def value if the value is nul or not
     * a Number.
     *
     * @param key
     * a String representing the key.
     * @return The Number at the key or def value
     */
    fun optNumber(key: String, def: Number?): Number? {
        return if (isNumber(key)) super.get(key) as Number? else def
    }

    /**
     * Return the specified key value or the def value if the value is nul or not
     * an ArrayList.
     *
     * @param key
     * a String representing the key.
     * @return The ArrayList at the key or def value
     */
    fun optArrayList(key: String, def: ArrayList<*>?): ArrayList<*>? {
        return if (isArrayList(key)) super.get(key) as ArrayList<*>? else def
    }

    /**
     * Return the specified key value or the def value if the value is nul or not
     * a ConcurrentHashMap.
     *
     * @param key
     * a String representing the key.
     * @return The ConcurrentHashMap at the key or def value
     */
    fun optMap(key: String, def: ConcurrentHashMap<String?, Any?>): KuzzleMap {
        return if (isMap(key)) from(super.get(key) as ConcurrentHashMap<String?, Any?>) else from(def)
    }

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = -3027862451021177820L

        /**
         * Convert à ConcurrentHashMap<String></String>, Object> to a CustomMap
         *
         * @param map
         * ConcurrentHashMap<String></String>, Object> representing JSON.
         * @return a CustomMap instance
         */
        fun from(map: ConcurrentHashMap<String?, Any?>): KuzzleMap {
            return if (map is KuzzleMap) {
                map
            } else KuzzleMap(map)
        }
    }
}
