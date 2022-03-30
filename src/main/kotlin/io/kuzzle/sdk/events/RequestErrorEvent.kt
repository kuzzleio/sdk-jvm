package io.kuzzle.sdk.events

class RequestErrorEvent {
    var exception: Exception
    var requestId: String?

    constructor(exception: Exception, requestId: String?) {
        this.exception = exception
        this.requestId = requestId
    }
}
