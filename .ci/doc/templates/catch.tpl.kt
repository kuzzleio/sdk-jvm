import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.*

import java.util.concurrent.ExecutionException
import io.kuzzle.sdk.coreClasses.responses.Response

fun main() {
  val protocol: AbstractProtocol;
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
    println("Error")
  } catch (e: Exception) {
    e.printStackTrace()
  } finally {
    kuzzle.disconnect()
  }
}
