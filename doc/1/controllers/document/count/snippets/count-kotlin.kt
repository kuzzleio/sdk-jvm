val searchQuery : Map<String, Any?> =
    HashMap<String, Any?>().apply {
      put("match", HashMap<String, Any?>().apply {
        put("Hello", "Clarisse")
      })
    }

val result: Int = kuzzle
    .documentController
    .count("nyc-open-data", "yellow-taxi", searchQuery)
    .get()
