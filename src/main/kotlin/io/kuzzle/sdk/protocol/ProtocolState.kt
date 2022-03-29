package io.kuzzle.sdk.protocol

enum class ProtocolState(val value: Int) {
    CLOSE(0), // The network protocol does not accept requests.
    OPEN(1), // The network protocol accepts new requests.
    RECONNECTING(2) // The nerwork protocol is trying to reconnect
}
