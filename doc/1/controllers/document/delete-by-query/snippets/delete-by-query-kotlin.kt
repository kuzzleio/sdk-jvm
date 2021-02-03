val searchQuery: Map<String, Any?> = Map<String, Any?>().apply {
  put("query", Map<String, Any?>().apply {
    put("match", Map<String, Any?>().apply {
      put("capacity", 4)
    })
  })
}

val result: ArrayList<String> = kuzzle
  .documentController
  .deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery)
  .get()
