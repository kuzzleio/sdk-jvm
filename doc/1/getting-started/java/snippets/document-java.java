import io.kuzzle.sdk.*;
import io.kuzzle.sdk.protocol.WebSocket;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] args) {

    try {
      // Creates a WebSocket connection.
      // Replace "kuzzle" with
      // your Kuzzle host name (e.g. "localhost")
      WebSocket ws = new WebSocket("kuzzle");

      // Instantiates a Kuzzle client
      kuzzle = new Kuzzle(ws);

      // Connects to the server.
      kuzzle.connect();
      System.out.println("Connected!");
    } catch(Exception e){
      e.printStackTrace();
    }

    // New document content
    Map<String, Object> content = new HashMap<>();
    content.put("name", "John");
    content.put("birthday", "1995-11-27");
    content.put("license", "B");

    // Stores the document in the "yellow-taxi" collection.
    try {
      kuzzle.getDocumentController()
          .create( "nyc-open-data", "yellow-taxi", content).get();
      System.out.println("New document added to the yellow-taxi collection!");
    } catch(Exception e){
      e.printStackTrace();
    }

    // Disconnects the SDK.
    kuzzle.disconnect();
  }
}
