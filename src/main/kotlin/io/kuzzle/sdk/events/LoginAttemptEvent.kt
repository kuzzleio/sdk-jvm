package io.kuzzle.sdk.events

class LoginAttemptEvent {
    var success: Boolean
    constructor(success: Boolean){
        this.success = success
    }
}
