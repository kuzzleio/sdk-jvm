val document: Map<String, Any?> =
  Map<String, Any?>().apply {
    put("firstname", "John")
  }

val result: Map<String, Any?> =
  kuzzle
  .documentController
  .replace("nyc-open-data", "yellow-taxi", "some-id", document)
  .get()
