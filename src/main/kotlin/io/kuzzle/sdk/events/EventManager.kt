package io.kuzzle.sdk.events

open class EventManager {
    private val listeners: HashMap<Any, Any> = HashMap()

    fun <T : Any> addListener(eventClass: Class<T>, listener: (T) -> Unit) {
        if (listeners[eventClass] == null) {
            listeners[eventClass] = mutableListOf(listener)
        } else {
            var list = listeners[eventClass] as MutableList<Any>
            list.add(listener)
        }
    }

    fun <T : Any> trigger(event: T) {
        if (listeners[event.javaClass] != null) {
            var invokerList = listeners[event.javaClass] as MutableList<(T) -> Unit>
            for (invokable: (T) -> Unit in invokerList) {
                invokable.invoke(event)
            }
        }
    }

    // Kotlin inlined version of addListener for better readability and usage
    inline fun <reified T : Any> addListener(noinline listener: (T) -> Unit) {
        addListener(T::class.java, listener)
    }
}
