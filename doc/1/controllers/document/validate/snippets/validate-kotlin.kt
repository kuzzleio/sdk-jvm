val document: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("key", "value")
  }


val result: ConcurrentHashMap<String, ArrayList<Any>> =
  kuzzle
  .documentController
  .validate("nyc-open-data", "yellow-taxi", document)
  .get()
  