package io.kuzzle.sdk.events

import io.kuzzle.sdk.protocol.ProtocolState

data class NetworkStateChangeEvent (var state: ProtocolState)
