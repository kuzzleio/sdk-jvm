package io.kuzzle.sdk.events

import io.kuzzle.sdk.protocol.ProtocolState

class NetworkStateChangeEvent {
    var state: ProtocolState
    constructor(state: ProtocolState) {
        this.state = state
    }
}
