package io.kuzzle.sdk.protocol

import io.kuzzle.sdk.events.EventManager
import java.util.concurrent.ConcurrentHashMap

abstract class AbstractProtocol: EventManager() {
  abstract var state: ProtocolState
  abstract fun connect()
  abstract fun disconnect()
  abstract fun send(payload: ConcurrentHashMap<String?, Any?>)
}
