import io.kuzzle.sdk.*;
import io.kuzzle.sdk.protocol.WebSocket;

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
    // Subscribes to notifications for drivers having a "B" driver license.
    val equals: Map<String, Any?> = HashMap<String, Any?>().apply {
      put("license", "B")
    }
    val filters: Map<String, Any> = HashMap<String, Any>().apply {
      put("equals", equals)
    }

    // Sends the subscription
    kuzzle
        .realtimeController
        .subscribe("nyc-open-data", "yellow-taxi", filters) { notification ->
          val content: Map<String, Any?> = notification.result as Map<String, Any?>
          println("New created document notification: " + content)  
          /*
            {
            _source={
              birthday=1995-11-27,
              license=B,
              name=John,
              _kuzzle_info={
                createdAt=1605694059151,author=-1
                }
              },
              _id=9PDS2nUBeGNr7nwl8j2Q,
              _version=1
            }
          */
        }.get();
    println("Successfully subscribed!")

    // Writes a new document. This triggers a notification
    // sent to our subscription.
    val content: Map<String, Any?> = HashMap<String, Any?>().apply {
      put("name", "John")
      put("birthday", "1995-11-27")
      put("license", "B")
    }

    kuzzle
        .documentController
        .create("nyc-open-data", "yellow-taxi", content).get();
    println("New document added to the yellow-taxi collection!")
  } catch (e: Exception) {
    e.printStackTrace()
  } finally {
    // Disconnects the SDK.
    kuzzle.disconnect()
  }
}
