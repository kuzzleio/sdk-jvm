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
            "POST",
            "/users",
            200,
            "{\"name\":\"albert\"}"
        )

        val httpProtol = Http("http://localhost:7512")
        httpProtol.send(kotlin.collections.mapOf())
    }
}