import io.kuzzle.sdk.*;
import io.kuzzle.sdk.protocol.WebSocket;
import java.util.*;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] args) {
    // @start
    try {
      // Creates a WebSocket connection.
      // Replace "kuzzle" with
      // your Kuzzle hostname like "localhost"
      WebSocket ws = new WebSocket("kuzzle");
      
      // Instantiates a Kuzzle client
      kuzzle = new Kuzzle(ws);
      
      // Connects to the server.
      kuzzle.connect();
      System.out.println("Connected!");
    } catch(Exception e){
      e.printStackTrace();
    }

    // Freshly installed Kuzzle servers are empty: we need to create
    // a new index.
    try {
      kuzzle.getIndexController().create("nyc-open-data").get();
      System.out.println("Index nyc-open-data created!");
    } catch(Exception e){
      e.printStackTrace();
    }

    // Creates a collection
    try {
      kuzzle.getCollectionController().create("nyc-open-data", "yellow-taxi").get();
      System.out.println("Collection yellow-taxi created!");
    } catch(Exception e){
      e.printStackTrace();
    }

    // Disconnects the SDK
    kuzzle.disconnect();
    // @end
  }
}
