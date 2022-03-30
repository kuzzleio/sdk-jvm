package io.kuzzle.sdk.events

data class RequestErrorEvent(var exception: Exception, var requestId: String?)
