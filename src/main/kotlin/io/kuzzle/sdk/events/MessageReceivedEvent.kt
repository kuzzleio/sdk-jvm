package io.kuzzle.sdk.events

class MessageReceivedEvent{
    var message: String?
    var requestId: String?
    constructor(message: String?, requestId: String?) {
        this.message = message
        this.requestId = requestId
    }
}
