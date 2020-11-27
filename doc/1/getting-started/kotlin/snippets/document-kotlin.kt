import io.kuzzle.sdk.*;
import io.kuzzle.sdk.protocol.WebSocket;

import java.util.concurrent.ConcurrentHashMap;

fun main() {

  // Creates a WebSocket connection.
  // Replace "kuzzle" with
  // your Kuzzle host name (e.g. "localhost")
  val ws = WebSocket("kuzzle");

  // Instantiates a Kuzzle client
  val kuzzle = Kuzzle(ws).apply {
  // Connects to the server.
    connect()
  }
  println("Connected!");

  try {
    // New document content
    val content: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>().apply {
      put("name", "John")
      put("birthday", "1995-11-27")
      put("license", "B")
    }
    // Stores the document in the "yellow-taxi" collection.
    kuzzle
        .documentController
        .create( "nyc-open-data", "yellow-taxi", content).get();
    println("New document added to the yellow-taxi collection!")
    
  } catch(e: Exception) {
    e.printStackTrace()
  } finally {
    // Disconnects the SDK.
    kuzzle.disconnect()
  }
}
