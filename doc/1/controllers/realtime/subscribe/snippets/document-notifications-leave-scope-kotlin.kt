val filters: Map<String, Any> = HashMap<String, Any>().apply {
  put("range", HashMap<String, Any>().apply {
    put("age", HashMap<String, Any>().apply {
      put("lte", 20)
    })
  })
}

val roomId: String = kuzzle.realtimeController.subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters) {
  if (it.scope == "in") {
    println("Document entered the scope")
  } else {
    println("Document moved in the scope")
  }
}.get()

val document = HashMap<String, Any>().apply {
  put("age", 19)
}
val query = HashMap<String?, Any?>().apply {
  put("controller", "document");
  put("action", "create");
  put("index", "nyc-open-data");
  put("collection", "yellow-taxi");
  put("_id", "nina-vkote");
  put("body", document);
}

kuzzle.query(query).get()

query.put("action", "update")
document.put("age", 42)
kuzzle.query(query).get()
