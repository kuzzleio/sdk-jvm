package io.kuzzle.sdk.events

class UnhandledResponseEvent {
    var message: String?
    constructor(message: String?) {
        this.message = message
    }
}
