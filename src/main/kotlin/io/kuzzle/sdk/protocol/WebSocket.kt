package io.kuzzle.sdk.protocol

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.client.features.websocket.wss
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread

class WebSocket : AbstractProtocol {
  private var ws: DefaultClientWebSocketSession? = null
  private val client = HttpClient {
    install(WebSockets)
  }
  private val host: String
  private val port: Int
  private val isSsl: Boolean

  private val queue: ConcurrentLinkedQueue<String> = ConcurrentLinkedQueue()

  @JvmOverloads
  constructor(host: String, port: Int = 7512, isSsl: Boolean = false) {
    this.host = host
    this.port = port
    this.isSsl = isSsl
  }

  override fun connect() {
    val wait = CompletableFuture<Void>()
    val block: suspend DefaultClientWebSocketSession.() -> Unit = {
      ws = this
      // @TODO Create enums for events
      super.trigger("networkStateChange", "open")
      wait.complete(null)
      for (frame in incoming) {
        when (frame) {
          // @TODO Create enums for events
          is Frame.Text -> super.trigger("messageReceived", frame.readText())
          // @TODO Create enums for events
          is Frame.Binary -> super.trigger("messageReceived", frame.readBytes().toString())
        }
      }
    }
    thread(start = true) {
      while (true) {
        val payload = queue.poll()
        if (payload != null) {
          GlobalScope.launch {
            ws?.send(Frame.Text(payload))
          }
        }
      }
    }
    GlobalScope.launch {
      if (isSsl) {
        client.wss(
            host = this@WebSocket.host,
            port = this@WebSocket.port,
            block = block
        )
      } else {
        client.ws(
            host = this@WebSocket.host,
            port = this@WebSocket.port,
            block = block
        )
      }
    }
    wait.get()
  }

  override fun disconnect() {
    TODO("not implemented")
  }

  override fun send(payload: String) {
    queue.add(payload)
  }
}