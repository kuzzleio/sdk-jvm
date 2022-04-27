import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.*

import java.util.concurrent.ExecutionException
import io.kuzzle.sdk.coreClasses.responses.Response
import io.kuzzle.sdk.coreClasses.SearchResult
import io.kuzzle.sdk.coreClasses.lang.Lang

fun main() {
  val protocol: AbstractProtocol;
  if (System.getenv("SNIPPET_PROTOCOL") != null && System.getenv("SNIPPET_PROTOCOL") == "http") {
    protocol = Http("kuzzle")
  } else {
    protocol = WebSocket("kuzzle")
  }
  val kuzzle = Kuzzle(protocol).apply {
    connect()
  }
  try {
    [snippet-code]
    println("Successfully retrieved " + matched.size + " documents");  } catch (e: Exception) {
    e.printStackTrace()
  } finally {
    kuzzle.disconnect()
  }
}
