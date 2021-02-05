val filters: Map<String, Any> = HashMap<String, Any>().apply {
  put("exists", "name")
}

val roomId: String = kuzzle.realtimeController.subscribe(
  "nyc-open-data",
  "yellow-taxi",
  filters) {
    if (it.scope == "in") {
      println("Document entered the scope")
    } else {
      println("Document left the scope")
    }
  }.get()

val result: Int = kuzzle.realtimeController.count(roomId).get()