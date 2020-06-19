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
val query: ConcurrentHashMap<String?, Any?> = ConcurrentHashMap<String?, Any?>().apply {
  put("controller", "document");
  put("action", "create");
  put("index", "nyc-open-data");
  put("collection", "yellow-taxi");
  put("_id", "nina-vkote");
  put("body", document);
}

kuzzle.query(query).get()