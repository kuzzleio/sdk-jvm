package io.kuzzle.sdk.protocol

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
import io.kuzzle.sdk.events.MessageReceivedEvent
import io.kuzzle.sdk.events.NetworkStateChangeEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

open class Http : AbstractProtocol {
    override var state: ProtocolState = ProtocolState.CLOSE
    private var uri: String

    @JvmOverloads
    constructor(
        host: String,
        port: Int = 7512,
        isSsl: Boolean = false
    ) {
      if (!isSsl) {
        this.uri = "http://${host}:${port}/_query"
      } else {
        this.uri = "https://${host}:${port}/_query"
      }
    }

    override fun connect () {
        if (this.state != ProtocolState.CLOSE) {
            return
        }

        val wait = CompletableFuture<Void>()
        // if state is NOT open, request /_query to see if we have the proper rights to make request using _query
        GlobalScope.launch {

            var client = HttpClient()
            var response: HttpResponse = client.post(uri) {
                this.header("content-type", "application/json")
                this.body = "{}"
            }
            client.close()
            if (response.status.value == 401 || response.status.value == 403) {
                wait.completeExceptionally(java.lang.Exception("You must have permission on the _query route."))
                return@launch
            }
            wait.complete(null)
        }

        wait.get()

        this.state = ProtocolState.OPEN
        trigger(NetworkStateChangeEvent(ProtocolState.OPEN))
    }

    override fun disconnect () {
        if (state != ProtocolState.OPEN) {
            return
        }

        this.state = ProtocolState.CLOSE
        trigger(NetworkStateChangeEvent(ProtocolState.CLOSE))
    }

    override fun send (payload: Map<String?, Any?>) {
        GlobalScope.launch { // Launch HTTP Request inside a coroutine to be non-blocking
            var client = HttpClient()
            var response: HttpResponse = client.post(uri) {
                this.header("content-type", "application/json")
                if (payload["jwt"] != null) {
                    this.header("Authorization", "Bearer ${payload["jwt"]}")
                }
                if (payload["volatile"] != null) {
                    this.header("Volatile", "${payload["volatile"]}")
                }
                this.body = JsonSerializer.serialize(payload)
            }
            client.close()
            // trigger messageReceived
            super.trigger(MessageReceivedEvent(response.receive(), payload["requestId"] as String?))
        }
    }
}
