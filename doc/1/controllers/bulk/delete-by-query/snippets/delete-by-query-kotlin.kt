val searchQuery: Map<String, Any?> = Map<String, Any?>().apply {
  put("query", Map<String, Any?>().apply {
    put("term", Map<String, Any?>().apply {
      put("capacity", 7)
    })
  })
}

val result: Int = kuzzle.bulkController.deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery).get()
