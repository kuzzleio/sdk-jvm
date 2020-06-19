val filters: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
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

val document: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
  put("name", "nina-vkote")
}

kuzzle.realtimeController.unsubscribe(roomId)