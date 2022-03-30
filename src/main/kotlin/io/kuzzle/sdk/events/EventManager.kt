package io.kuzzle.sdk.events

open class EventManager {
    protected val listeners: HashMap<Any, Any> = HashMap()

    // Java version of addListener function
    fun <T : Any> addListener(eventClass: Class<T>, listener: (T) -> Unit) {
        if (listeners[eventClass] == null) {
            listeners[eventClass] = mutableListOf(listener)
        } else {
            var list = listeners[eventClass] as MutableList<Any>
            list.add(listener)
        }
    }

    // Kotlin inlined version of addListener for better readability and usage
    inline fun <reified T : Any> addListener(noinline listener: (T) -> Unit) {
        addListener(T::class.java, listener)
    }

    // Kotlin version of trigger function, no need for Java version since
    internal inline fun <reified T : Any> trigger(event: T) {
        if (listeners[T::class.java] != null) {
            var invokerList = listeners[T::class.java] as MutableList<(T) -> Unit>
            for (invokable: (T) -> Unit in invokerList) {
                invokable.invoke(event)
            }
        }
    }
}
