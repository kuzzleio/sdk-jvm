val document: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>().apply {
  put("firstname", "John")
}

val result: ConcurrentHashMap<String, Any?> =
    kuzzle
        .documentController
        .createOrReplace("nyc-open-data", "yellow-taxi", "some-id", document)
        .get();