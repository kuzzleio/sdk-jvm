import io.kuzzle.sdk.Kuzzle
import io.kuzzle.sdk.protocol.WebSocket
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ExecutionException

fun main() {
  try {
    [snippet-code]
    println("Success")
  } catch (e: Exception) {
    e.printStackTrace()
  }
}
