val searchQuery: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>().apply {
  put("query", ConcurrentHashMap<String, Any?>().apply {
    put("term", ConcurrentHashMap<String, Any?>().apply {
      put("capacity", 7)
    })
  })
}

val result: Int = kuzzle.bulkController.deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery).get()
