package io.kuzzle.sdk.events

open class EventManager {
    val listeners: HashMap<Any, Any> = HashMap()

    // Java version of addListener function
    fun <T : IEvent> addListener(eventClass: Class<T>, listener: (T) -> Unit) {
        if (listeners[eventClass] == null) {
            listeners[eventClass] = mutableListOf(listener)
        } else {
            var list = listeners[eventClass] as MutableList<Any>
            list.add(listener)
        }
    }

    // Kotlin version of addListener function
    inline fun <reified T : IEvent> addListener(noinline listener: (T) -> Unit) {
        if (listeners[T::class.java] == null) {
            listeners[T::class.java] = mutableListOf(listener)
        } else {
            var list = listeners[T::class.java] as MutableList<Any>
            list.add(listener)
        }
    }

    // Kotlin version of trigger function, no need for Java version since
    internal inline fun <reified T : IEvent> trigger(event: T) {
        if (listeners[T::class.java] != null) {
            var invokerList = listeners[T::class.java] as MutableList<(T) -> Unit>
            for (invokable: (T) -> Unit in invokerList) {
                invokable.invoke(event)
            }
        }
    }
}
