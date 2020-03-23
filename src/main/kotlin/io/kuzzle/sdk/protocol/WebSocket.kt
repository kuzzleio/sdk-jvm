package io.kuzzle.sdk.protocol

import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.client.features.websocket.wss
import io.ktor.http.ContentType
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readBytes
import io.ktor.http.cio.websocket.readText
import io.ktor.util.KtorExperimentalAPI
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread

open class WebSocket : AbstractProtocol {
  protected open var ws: DefaultClientWebSocketSession? = null

  @KtorExperimentalAPI
  protected open var client = HttpClient {
    install(WebSockets)
    install(JsonFeature) {
      serializer = GsonSerializer()
      acceptContentTypes += ContentType("application", "json")
    }
  }
  private val host: String
  private val port: Int
  private val isSsl: Boolean
  private val queue: ConcurrentLinkedQueue<String> = ConcurrentLinkedQueue()
  override var state: ProtocolState = ProtocolState.CLOSE

  @JvmOverloads
  constructor(host: String, port: Int = 7512, isSsl: Boolean = false) {
    this.host = host
    this.port = port
    this.isSsl = isSsl
  }

  @KtorExperimentalAPI
  override fun connect() {
    val wait = CompletableFuture<Void>()
    val block: suspend DefaultClientWebSocketSession.() -> Unit = {
      ws = this
      // @TODO Create enums for events
      super.trigger("networkStateChange", "open")
      state = ProtocolState.OPEN
      thread(start = true) {
        while (ws != null) {
          val payload = queue.poll()
          if (payload != null) {
            GlobalScope.launch {
              ws?.send(Frame.Text(payload))
            }
          }
        }
      }
      wait.complete(null)
      for (frame in incoming) {
        when (frame) {
          // @TODO Create enums for events
          is Frame.Text -> super.trigger("messageReceived", frame.readText())
          // @TODO Create enums for events
          is Frame.Binary -> super.trigger("messageReceived", frame.readBytes().toString())
        }
      }
      ws = null
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
    state = ProtocolState.CLOSE
    GlobalScope.launch {
      ws?.close()
      ws = null
    }
  }

  override fun send(payload: ConcurrentHashMap<String?, Any?>) {
    queue.add(JsonSerializer.serialize(payload))
  }
}