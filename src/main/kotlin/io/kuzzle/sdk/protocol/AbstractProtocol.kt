package io.kuzzle.sdk.protocol

import io.kuzzle.sdk.events.EventManager


abstract class AbstractProtocol: EventManager() {
  abstract var state: ProtocolState
  abstract fun connect()
  abstract fun disconnect()
  abstract fun send(payload: Map<String?, Any?>)
}
