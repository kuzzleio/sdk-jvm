val document: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("firstname", "John")
  }

val result: ConcurrentHashMap<String, ArrayList<Any>> =
  kuzzle
  .documentController
  .replace("nyc-open-data", "yellow-taxi", "some-id", document)
  .get()
