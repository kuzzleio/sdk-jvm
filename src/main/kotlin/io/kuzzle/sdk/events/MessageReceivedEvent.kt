package io.kuzzle.sdk.events

class MessageReceivedEvent : IEvent {
    var message: String?
    var requestId: String?
    constructor(message: String?, requestId: String?) : super(1) {
        this.message = message
        this.requestId = requestId
    }
}