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
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Subscribes to notifications for drivers having a "B" driver license.
    Map<String, Object> filters = new HashMap<>();
    Map<String, Object> equals = new HashMap<>();
    equals.put("license", "B");
    filters.put("equals", equals);

    try {
      // Sends the subscription
      kuzzle.getRealtimeController()
          .subscribe("nyc-open-data", "yellow-taxi", filters, notification -> {
            Map<String, Object> content = ((Map<String, Object>)(notification.getResult()));
            System.out.println("New created document notification: " + content);
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
          }).get();
      System.out.println("Successfully subscribed!");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Writes a new document. This triggers a notification
    // sent to our subscription.
    Map<String, Object> content = new HashMap<>();
    content.put("name", "John");
    content.put("birthday", "1995-11-27");
    content.put("license", "B");

    try {
      kuzzle.getDocumentController()
          .create("nyc-open-data", "yellow-taxi", content).get();
      System.out.println("New document added to the yellow-taxi collection!");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Disconnects the SDK.
    kuzzle.disconnect();
    // @end
  }
}
