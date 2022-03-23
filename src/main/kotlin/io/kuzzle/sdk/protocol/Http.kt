package io.kuzzle.sdk.protocol

import com.google.gson.Gson
import io.kuzzle.sdk.coreClasses.json.JsonSerializer
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
   * @param url request uri
   */
  constructor(url: String) {
    this.request.uri(URI.create("$url/_query"))
    this.state = ProtocolState.OPEN
  }

  override fun connect () {
    if (this.state == ProtocolState.OPEN) {
      return
    }
    // if state is NOT open, return response to /_publicApi
    this.request.uri(URI.create("http://localhost:7512/_publicApi"))
    val response = client.send(request.build(), HttpResponse.BodyHandlers.ofString())
    if (response.statusCode() == 401 || response.statusCode() == 403) {
     throw Exception("You must have permission on the _query route.")
    }
  }

  override fun disconnect () {
    this.state = ProtocolState.CLOSE
  }

  override fun send (payload: Map<String?, Any?>) {
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
    val response = client.send(request.POST(HttpRequest.BodyPublishers.ofString(JsonSerializer.serialize(payload))).build(), HttpResponse.BodyHandlers.ofString())
    // trigger messageReceived
    super.trigger("messageReceived", response.body().toString())
  }
}
