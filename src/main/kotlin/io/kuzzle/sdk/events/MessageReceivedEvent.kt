package io.kuzzle.sdk.events

import io.ktor.http.*

data class MessageReceivedEvent(
    var message: String?,
    var payload: Map<String?, Any?> = emptyMap(),
    var status: Int = 200,
    var headers: Headers? = null
) {
    fun getHeadersMap(): Map<String, String> {
        if (headers == null) {
            return emptyMap()
        }
        return headers!!.entries().associate { it.key to it.value.first() }
    }
}
