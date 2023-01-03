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
  for (item: Any? in result) {
    println(item.toString())
  }
  println(result.toString())
} catch (e: Exception) {
  e.printStackTrace()
} finally {
  kuzzle.disconnect()
}