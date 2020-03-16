import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.WebSocket
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
  val ws = WebSocket("localhost")
  val k = Kuzzle(ws)
  runBlocking {
    launch {
      k.connect()
      println(k.query("{\"controller\":\"server\", \"action\":\"now\"}").get())
    }
  }
}