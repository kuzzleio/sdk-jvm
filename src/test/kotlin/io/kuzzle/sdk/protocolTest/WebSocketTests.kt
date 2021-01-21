package io.kuzzle.sdk.protocolTest

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.ContentType
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.util.KtorExperimentalAPI
import io.kuzzle.sdk.protocol.ProtocolState
import io.kuzzle.sdk.protocol.WebSocket
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.util.concurrent.ConcurrentHashMap

class WebSocketTests {
    inner class MockedSocket(host: String) : WebSocket(host) {
        @KtorExperimentalAPI
        var mockedClient: HttpClient
            get() = super.client
            set(value) {
                super.client = value
            }
    }

    private var socket: MockedSocket? = null

    @KtorExperimentalAPI
    @Before
    fun setup() {
        socket = MockedSocket("localhost")
        socket?.mockedClient = HttpClient(MockEngine) {
            install(JsonFeature) {
                serializer = GsonSerializer()
                acceptContentTypes += ContentType("application", "json")
            }
            engine {
                addHandler { request ->
                    when (request.url.toString()) {
                        "ws://localhost:7512/" -> {
                            val responseHeaders = headersOf("Content-Type" to listOf("application/json"))
                            respond("{}", headers = responseHeaders)
                        }
                        else -> error("Unhandled ${request.url.fullPath}")
                    }
                }
            }
        }
    }

    @Test
    fun constructorNotConnected() {
        assertEquals(ProtocolState.CLOSE, socket?.state)
    }

    @KtorExperimentalAPI
    @Test
    fun connectTest() {
        assertEquals(ProtocolState.CLOSE, socket?.state)
        socket?.connect()
        assertNotEquals(ProtocolState.CLOSE, socket?.state)
        socket?.disconnect()
        socket = null
    }

    @KtorExperimentalAPI
    @Test
    fun sendTest() {
        val query = ConcurrentHashMap<String?, Any?>().apply {
            put("controller", "server")
            put("action", "now")
        }

        socket?.connect()
        socket?.send(query)
        socket?.disconnect()
        socket = null
    }
}
