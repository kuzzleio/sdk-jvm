package io.kuzzle.sdk.events

open class EventManager {
    private val listeners: HashMap<String, (Array<Any?>?) -> Unit> = HashMap()

    fun addListener(event: String, listener: (Array<Any?>?) -> Unit) {
        listeners[event] = listener
    }

    fun trigger(event: String, args: Array<Any?>?) {
        listeners[event]?.invoke(args)
    }
}
