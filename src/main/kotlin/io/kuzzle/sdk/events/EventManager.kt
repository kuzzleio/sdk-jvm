package io.kuzzle.sdk.events

open class EventManager {
  private val listeners: HashMap<String, (String) -> Unit> = HashMap()

  fun addListener(event: String, listener: (String) -> Unit) {
    listeners[event] = listener
  }

  fun trigger(event: String, message: String) {
    listeners[event]?.invoke(message)
  }
}