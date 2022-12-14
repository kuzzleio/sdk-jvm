package io.kuzzle.sdk.events

data class MessageReceivedEvent(
    var message: String?,
    var payload: Map<String?, Any?> = emptyMap(),
    var status: Int = 200,
    var headers: Map<String, List<String>>? = null
)
