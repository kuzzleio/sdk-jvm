import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.Http
import java.util.Date

import java.util.concurrent.ExecutionException
import io.kuzzle.sdk.coreClasses.lang.Lang
import io.kuzzle.sdk.coreClasses.responses.Response

fun main() {
  val protocol;
  if (System.getenv("SNIPPET_PROTOCOL") == "http") {
    protocol = Http("http://kuzzle:7512")
  } else {
    protocol = WebSocket("kuzzle")
  }
  try {
    [snippet-code]
    println(result.toString())
  } catch (e: Exception) {
    e.printStackTrace()
  } finally {
    kuzzle.disconnect()
  }
}
