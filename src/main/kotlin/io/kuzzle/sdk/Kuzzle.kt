package io.kuzzle.sdk

import com.google.gson.GsonBuilder
import io.kuzzle.sdk.protocol.AbstractProtocol
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

class Kuzzle(private val protocol: AbstractProtocol) {
  private val queries: HashMap<String, CompletableFuture<Object>> = HashMap()

  private fun onMessageReceived(message: String) {
    /* @TODO deserialize with GSON to get the requestId and send the json as parameter
     * Reimplement ConcurentHashMapAdapter to parse null values etc...
     */
    queries["42"]?.complete(message as Object)
  }

  init {
    // @TODO Create enums for events
    protocol.addListener("messageReceived", ::onMessageReceived)
  }

  fun connect() {
    protocol.connect()
  }

  fun query(query: String): CompletableFuture<Object> {
    val futureRes: CompletableFuture<Object> = CompletableFuture()
    //@TODO get the requestId once parsed with GSON
    queries["42"] = futureRes
    protocol.send(query)
    return futureRes
  }
}