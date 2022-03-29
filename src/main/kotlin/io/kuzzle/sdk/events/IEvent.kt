package io.kuzzle.sdk.events

abstract class IEvent {
    var eventId: Int

    constructor(eventId: Int) {
        this.eventId = eventId
    }
}