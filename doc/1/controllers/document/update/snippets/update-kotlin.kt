val document: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("name", "Johny")
  }

val result: Map<String, Any?> =
  kuzzle
  .documentController
  .update("nyc-open-data", "yellow-taxi", "some-id", document)
  .get()
