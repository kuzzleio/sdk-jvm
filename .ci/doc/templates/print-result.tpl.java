import io.kuzzle.sdk.Kuzzle;
import io.kuzzle.sdk.protocol.WebSocket;
import java.util.Date;
import io.kuzzle.sdk.coreClasses.responses.Response;
import java.util.ArrayList;

public class SnippetTest {
  private static Kuzzle kuzzle;

  public static void main(String[] argv) {
    try {
      kuzzle = new Kuzzle(new WebSocket("kuzzle"));
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