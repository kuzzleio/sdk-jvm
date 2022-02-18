package io.kuzzle.sdk.protocol

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

open class Http : AbstractProtocol {
  override var state: ProtocolState = ProtocolState.OPEN
  private val request = HttpRequest.newBuilder()
  private val client = HttpClient.newBuilder().build()
  

  /**
   * Contrsructor of the Http protocol
   * @param Uri request uri
   */
  constructor(url: String) {
    if (url.contains("/_query")) {
      this.request.uri(URI.create(url))
    } else {
      this.request.uri(URI.create("$url/_query"))
    }
    this.state = ProtocolState.OPEN
  }

  override fun connect() {
    // if state is not ready
    // if state is NOT ready
  }

  override fun disconnect() {
    // state = ProtocolState.CLOSE
    this.state = ProtocolState.CLOSE
  }

  override fun send(payload: Map<String?, Any?>) {
    // Create header
    this.request.header("Content-Type", "application/json")
    // Get jwt
    if (payload["jwt"] != null) {
      this.request.header("Authorization", "Bearer ${payload["jwt"]}")
    }
    // Get volatile
    if (payload["volatile"] != null) {
      this.request.header("Volatile", "${payload["volatile"]}")
    }
    // Send request
    val response = client.send(request.build(), HttpResponse.BodyHandlers.ofString())
    // trigger messageReceived
    super.trigger("messageReceived", response.body())
    println("response: $response")
  }
}
