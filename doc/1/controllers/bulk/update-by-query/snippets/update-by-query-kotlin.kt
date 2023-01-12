val searchQuery: Map<String, Any?> = HashMap<String, Any?>().apply {
  put("query", HashMap<String, Any?>().apply {
    put("term", HashMap<String, Any?>().apply {
      put("capacity", 7)
    })
  })
}

val changes: Map<String, Any?> = HashMap<String, Any?>().apply {
  put("changes", HashMap<String, Any?>().apply {
      put("capacity", 3)
  })
}

val result: Int = kuzzle.bulkController.updateByQuery("nyc-open-data", "yellow-taxi", searchQuery, changes).get()
