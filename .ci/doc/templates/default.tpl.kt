import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.WebSocket

import java.util.concurrent.ExecutionException
import io.kuzzle.sdk.coreClasses.responses.Response
import io.kuzzle.sdk.coreClasses.SearchResult
import io.kuzzle.sdk.coreClasses.lang.Lang

fun main() {
  val ws = WebSocket("kuzzle")
  val kuzzle = Kuzzle(ws).apply {
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
