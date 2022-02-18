package io.kuzzle.sdk.protocolTest

import org.mockserver.client.server.MockServerClient
import org.mockserver.integration.ClientAndServer
import kotlin.test.*
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import io.kuzzle.sdk.protocol.Http

internal fun MockServerClient.setup(
    requestMethod:String,
    requestPath:String,
    responseStatus: Int,
    responseBody:String
) {

    this.`when`(
        request()
            .withMethod(requestMethod)
            .withPath(requestPath)

    )
        .respond(
            response()
                .withStatusCode(responseStatus)
                .withBody(responseBody)
        )
}

internal fun MockServerClient.verifyRequest(
    method: String?,
    path: String?
) {
    val request = request()

    method?.let { request.withMethod(method) }
    path?.let { request.withPath(path) }

    this.verify(request)
}

abstract class HttpTestBase {
    private val port = 7512
    var mockServer: MockServerClient = MockServerClient("localhost", port)
    val url = "http://localhost:$port"

    @BeforeTest
    fun prepare () {
        mockServer = ClientAndServer.startClientAndServer(port)
    }

    @AfterTest
    fun tearDown () {
        mockServer.close()
    }
}

class HttpTests: HttpTestBase() {
    @Test
    fun SendValidRequestTest () {
        mockServer.setup(
            "GET",
            "/ping/_query",
            200,
            "ping"
        )

        val httpProtol = Http("http://localhost:7512/ping")
        httpProtol.send(kotlin.collections.mapOf())
        mockServer.verifyRequest(
            "GET",
            "/ping/_query"
        )
    }

    @Test
    fun ConnectTestWithoutError () {
        mockServer.setup(
            "GET",
            "/_publicApi",
            200,
            "_publicApi"
        )

        val httpProtol = Http("http://localhost:7512/")
        httpProtol.disconnect()
        httpProtol.connect()
        mockServer.verifyRequest(
            "GET",
            "/_publicApi"
        )
    }
}