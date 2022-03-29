package io.kuzzle.sdk.events

import io.kuzzle.sdk.protocol.ProtocolState

class NetworkStateChangeEvent : IEvent {
    var state: ProtocolState
    constructor(state: ProtocolState) : super(2) {
        this.state = state
    }
}
