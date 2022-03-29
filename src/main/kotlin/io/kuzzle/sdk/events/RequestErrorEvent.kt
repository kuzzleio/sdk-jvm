package io.kuzzle.sdk.events

class RequestErrorEvent : IEvent {
    var exception: Exception
    var requestId: String?

    constructor(exception: Exception, requestId: String?) : super(5) {
        this.exception = exception
        this.requestId = requestId
    }
}
