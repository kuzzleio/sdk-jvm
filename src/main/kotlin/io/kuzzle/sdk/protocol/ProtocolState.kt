package io.kuzzle.sdk.protocol

enum class ProtocolState {
  CLOSE,  // The network protocol does not accept requests.
  OPEN
  // The network protocol accepts new requests.
}