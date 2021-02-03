val document: Map<String, Any?> = Map<String, Any?>().apply {
  put("firstname", "John")
}

val result: Map<String, Any?> =
    kuzzle
      .documentController
      .create("nyc-open-data", "yellow-taxi", document)
      .get();