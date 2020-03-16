package io.kuzzle.sdk.protocol

import io.kuzzle.sdk.events.EventManager

abstract class AbstractProtocol: EventManager() {
  abstract fun connect()
  abstract fun disconnect()
  abstract fun send(payload: String)
}
