package io.kuzzle.sdk.events

class UnhandledResponseEvent : IEvent {
    var message: String?
    constructor(message: String?) : super(4) {
        this.message = message
    }
}
