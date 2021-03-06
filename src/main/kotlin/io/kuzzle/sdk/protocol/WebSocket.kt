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
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

open class WebSocket : AbstractProtocol {
    protected open var ws: DefaultClientWebSocketSession? = null
    private val host: String
    private val port: Int
    private val isSsl: Boolean
    private val queue: ConcurrentLinkedQueue<String> = ConcurrentLinkedQueue()
    override var state: ProtocolState = ProtocolState.CLOSE
    private val autoReconnect: Boolean
    private val reconnectionDelay: Long
    private val reconnectionRetries: Long
    private val stopRetryingToConnect: AtomicBoolean = AtomicBoolean(false)

    @KtorExperimentalAPI
    protected open var client = HttpClient {
        install(WebSockets)
        install(JsonFeature) {
            serializer = GsonSerializer()
            acceptContentTypes += ContentType("application", "json")
        }
    }

    @JvmOverloads
    constructor(
        host: String,
        port: Int = 7512,
        isSsl: Boolean = false,
        autoReconnect: Boolean = true,
        reconnectionDelay: Long = 1000,
        reconnectionRetries: Long = 60
    ) {
        this.host = host
        this.port = port
        this.isSsl = isSsl
        this.autoReconnect = autoReconnect
        this.reconnectionDelay = reconnectionDelay
        this.reconnectionRetries = reconnectionRetries
    }

    @KtorExperimentalAPI
    private fun tryToReconnect(): CompletableFuture<Boolean> {
        if (!autoReconnect || this.stopRetryingToConnect.get())
            return CompletableFuture.completedFuture(false)

        state = ProtocolState.RECONNECTING
        trigger("networkStateChange", state.toString())
        return CompletableFuture.supplyAsync(
            fun(): Boolean {
                var retryCount: Long = 0
                while ((reconnectionRetries == -1L || retryCount < reconnectionRetries) && !this.stopRetryingToConnect.get()) {
                    // If not infinite, increment retryCount
                    if (reconnectionRetries != -1L)
                        retryCount++

                    Thread.sleep(reconnectionDelay)
                    try {
                        connect()
                        return true
                    } catch (e: Exception) {
                        // Nothing to do, just retry
                    }
                }
                this.stopRetryingToConnect.set(false)
                return false
            }
        )
    }

    @KtorExperimentalAPI
    override fun connect() {
        if (this.stopRetryingToConnect.get())
            throw Exception("Connection Aborted")

        val wait = CompletableFuture<Void>()
        val block: suspend DefaultClientWebSocketSession.() -> Unit = {
            ws = this
            // @TODO Create enums for events
            state = ProtocolState.OPEN
            trigger("networkStateChange", ProtocolState.OPEN.toString())

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
            var skip = false
            try {
                for (frame in incoming) {
                    when (frame) {
                        // @TODO Create enums for events
                        is Frame.Text -> super.trigger("messageReceived", frame.readText())
                        // @TODO Create enums for events
                        is Frame.Binary -> super.trigger("messageReceived", frame.readBytes().toString())
                    }
                }
            } catch (e: Exception) {
                skip = true
                tryToReconnect().thenApply(
                    fun (success: Boolean) {
                        if (!success) {
                            state = ProtocolState.CLOSE
                            trigger("networkStateChange", ProtocolState.CLOSE.toString())
                            ws = null
                        }
                        // reset stopRetryingToConnect
                        stopRetryingToConnect.set(false)
                    }
                )
            }
            if (!skip) {
                state = ProtocolState.CLOSE
                trigger("networkStateChange", ProtocolState.CLOSE.toString())
                ws = null
            }
        }
        GlobalScope.launch {
            try {
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

                // On connection success
                stopRetryingToConnect.set(false)

                // This thread is here to let JAVA run until the socket is closed
                // In Kotlin this is handled by the block function above but for some reason in JAVA it is
                // non blocking.
                thread(start = true) {
                    while (true) {}
                }
            } catch (e: Exception) {
                when (e) {
                    is ConnectException,
                    is SocketException,
                    is IOException -> {
                        if (state != ProtocolState.RECONNECTING) {
                            tryToReconnect().thenAcceptAsync(
                                fun (success: Boolean) {
                                    if (success) {
                                        wait.complete(null)
                                    } else {
                                        wait.completeExceptionally(e)
                                    }
                                    stopRetryingToConnect.set(false)
                                }
                            )
                        } else {
                            wait.completeExceptionally(e)
                        }
                    } else -> throw e
                }
            }
        }
        wait.get()
    }

    override fun disconnect() {
        if (state != ProtocolState.CLOSE) {
            state = ProtocolState.CLOSE
            trigger("networkStateChange", ProtocolState.CLOSE.toString())
            stopRetryingToConnect.set(true)
            GlobalScope.launch {
                ws?.close()
                ws = null
            }
        }
    }

    override fun send(payload: Map<String?, Any?>) {
        queue.add(JsonSerializer.serialize(payload))
    }
}
