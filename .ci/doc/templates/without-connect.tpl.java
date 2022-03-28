import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.protocol.*;
import java.util.*;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      AbstractProtocol protocol;
      if (System.getenv("SNIPPET_PROTOCOL") != null && System.getenv("SNIPPET_PROTOCOL").equals("http")) {
        protocol = new Http("kuzzle");
      } else {
        protocol = new WebSocket("kuzzle");
      }
      Kuzzle kuzzle = new Kuzzle(protocol);
      [snippet-code]
      System.out.println("Success");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    } finally {
      if (kuzzle != null) {
        kuzzle.disconnect();
      }
    }
  }
}
