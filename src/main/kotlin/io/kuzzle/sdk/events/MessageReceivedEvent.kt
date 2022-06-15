package io.kuzzle.sdk.events

data class MessageReceivedEvent(var message: String?, var requestId: String? = null, var headers: Map<String, List<String>>? = null)
