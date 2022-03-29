package io.kuzzle.sdk.events

class LoginAttemptEvent : IEvent {
    var success: Boolean
    constructor(success: Boolean) : super(0) {
        this.success = success
    }
}
