import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.WebSocket

import java.util.concurrent.ExecutionException

fun main() {
  val ws = WebSocket("kuzzle")
  val kuzzle = Kuzzle(ws)
  try {
    [snippet-code]
    println("Success")
  } catch (e: Exception) {
    e.printStackTrace()
  } finally {
    kuzzle.disconnect()
  }
}