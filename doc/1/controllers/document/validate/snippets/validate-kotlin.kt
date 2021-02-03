val document: Map<String, Any?> =
  Map<String, Any?>().apply {
    put("key", "value")
  }


val result: Boolean =
  kuzzle
  .documentController
  .validate("nyc-open-data", "yellow-taxi", document)
  .get()
  