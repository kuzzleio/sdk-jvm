val searchQuery: Map<String, Any?> = HashMap<String, Any?>().apply {
  put("query", HashMap<String, Any?>().apply {
    put("term", HashMap<String, Any?>().apply {
      put("capacity", 7)
    })
  })
}

val result: Int = kuzzle.bulkController.deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery).get()
