import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.*

import java.util.concurrent.ExecutionException

fun main() {
  val protocol;
  if (System.getenv("SNIPPET_PROTOCOL") == "http") {
    protocol = Http("kuzzle")
  } else {
    protocol = WebSocket("kuzzle")
  }
  val kuzzle = Kuzzle(protocol).apply {
    connect()
  }
  try {
    [snippet-code]
    println("Success")
  } catch (e: Exception) {
    e.printStackTrace()
  } finally {
    kuzzle.disconnect()
  }
}