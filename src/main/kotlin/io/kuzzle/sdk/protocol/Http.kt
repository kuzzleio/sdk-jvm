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
  private var uri = ""


  @JvmOverloads
  constructor(
    host: String,
    port: String = "7512",
    isSsl: Boolean = false,
  ) {

    if (!isSsl) {
      this.uri = "http://${host}:${port}/_query"
    } else {
      this.uri = "https://${host}:${port}/_query"
    }
    this.request.uri(URI.create(this.uri))
    this.state = ProtocolState.OPEN
  }

  override fun connect () {
    if (this.state == ProtocolState.OPEN) {
      trigger("networkStateChange", ProtocolState.OPEN.toString())
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
    trigger("networkStateChange", ProtocolState.CLOSE.toString())
  }

  override fun send (payload: Map<String?, Any?>) {
    if (payload["requestId"] != null) {
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
      var response = client.send(
        request.POST(HttpRequest.BodyPublishers.ofString(JsonSerializer.serialize(payload))).build(),
        HttpResponse.BodyHandlers.ofString()
      ).body().toString()

      // get initial requestId
      var tmp = mutableMapOf<String?, Any?>()
      for ((k, v) in JsonSerializer.deserialize(response) as Map<String?, Any?>) {
        tmp.put(k, v)
      }
      tmp["requestId"] = payload.get("requestId")
      // trigger messageReceived
      super.trigger("messageReceived", JsonSerializer.serialize(tmp))
    }
  }
}
