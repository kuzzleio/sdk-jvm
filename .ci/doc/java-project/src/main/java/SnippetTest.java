import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.protocol.WebSocket;
import io.kuzzle.sdk.coreClasses.responses.Response;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      kuzzle.connect();
        ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
        document.put("key", "value");

        Boolean result = kuzzle
        .getDocumentController()
        .validate("nyc-open-data", "yellow-taxi", document)
        .get();
      System.out.println(result.toString());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (kuzzle != null) {
        kuzzle.disconnect();
      }
    }
  }
}