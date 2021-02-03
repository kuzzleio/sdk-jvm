val searchQuery : Map<String, Any?> =
    Map<String, Any?>().apply {
      put("match", Map<String, Any?>().apply {
        put("Hello", "Clarisse")
      })
    }

val result: Int = kuzzle
  .documentController
  .count("nyc-open-data", "yellow-taxi", searchQuery)
  .get();