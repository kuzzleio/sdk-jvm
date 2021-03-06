import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.WebSocket
import java.util.Date

import java.util.concurrent.ExecutionException
import io.kuzzle.sdk.coreClasses.lang.Lang
import io.kuzzle.sdk.coreClasses.responses.Response

fun main() {
  val ws = WebSocket("kuzzle")
  val kuzzle = Kuzzle(ws).apply {
    connect()
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
