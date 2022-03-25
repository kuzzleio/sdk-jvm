import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.protocol.*;
import io.kuzzle.sdk.coreClasses.responses.Response;
import io.kuzzle.sdk.coreClasses.lang.Lang;
import java.util.*;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      AbstractProtocol protocol;
      if (System.getenv("SNIPPET_PROTOCOL") == "http") {
        protocol = new Http("kuzzle");
      } else {
        protocol = new WebSocket("kuzzle");
      }
      Kuzzle kuzzle = new Kuzzle(protocol);
        protocol = Http("http://kuzzle:7512")
      } else {
        protocol = WebSocket("kuzzle")
      }
      Kuzzle kuzzle = Kuzzle(protocol);
      kuzzle.connect();
      [snippet-code]
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