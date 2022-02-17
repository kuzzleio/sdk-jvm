package io.kuzzle.sdk.protocol

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

open class Http : AbstractProtocol {
  override var state: ProtocolState = ProtocolState.CLOSE
  private val request = HttpRequest.newBuilder()
  private val client = HttpClient.newBuilder().build()
  

  /**
   * Contrsructor of the Http protocol
   * @param Uri request uri
   */
  constructor(uri: URI) {
    this.state = ProtocolState.OPEN
    this.request.uri(uri)
  }

  override fun connect() {
    // if state is not ready
    // Get on publicapi
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
    println("response: $response")
  }
}
