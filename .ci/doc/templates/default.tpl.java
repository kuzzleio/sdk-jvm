import io.kuzzle.sdk.Kuzzle;
import java.util.ArrayList;
import io.kuzzle.sdk.protocol.WebSocket;
import io.kuzzle.sdk.coreClasses.responses.Response;
import io.kuzzle.sdk.coreClasses.SearchResult;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      kuzzle = new Kuzzle(new WebSocket("kuzzle"));
      kuzzle.connect();
      [snippet-code]
      System.out.println("Success");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (kuzzle != null) {
        kuzzle.disconnect();
      }
    }
  }
}