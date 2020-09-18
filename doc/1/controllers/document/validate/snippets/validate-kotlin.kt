val document: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("key", "value")
  }


val result: Boolean =
  kuzzle
  .documentController
  .validate("nyc-open-data", "yellow-taxi", document)
  .get()
  