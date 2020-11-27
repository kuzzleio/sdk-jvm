import io.kuzzle.sdk.*;
import io.kuzzle.sdk.protocol.WebSocket;

import java.util.concurrent.ConcurrentHashMap;

fun main() {

  // Creates a WebSocket connection.
  // Replace "kuzzle" with
  // your Kuzzle hostname like "localhost"
  val ws = WebSocket("kuzzle");

  // Instantiates a Kuzzle client
  val kuzzle = Kuzzle(ws).apply {
  // Connects to the server.
    connect()
  };
  println("Connected!")

  try {

    // Freshly installed Kuzzle servers are empty: we need to create
    // a new index.
    kuzzle
        .indexController
        .create("nyc-open-data").get();
    println("Index nyc-open-data created!")

    // Creates a collection
    kuzzle
        .collectionController
        .create("nyc-open-data", "yellow-taxi").get();
    println("Collection yellow-taxi created!")
  } catch(e: Exception){
    e.printStackTrace()
  } finally {
    // Disconnects the SDK
    kuzzle.disconnect()
  }
}
