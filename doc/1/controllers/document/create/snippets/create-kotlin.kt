val document: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>().apply {
  put("firstname", "John")
}

val result: ConcurrentHashMap<String, Any?> =
    kuzzle
      .documentController
      .create("nyc-open-data", "yellow-taxi", document)
      .get();