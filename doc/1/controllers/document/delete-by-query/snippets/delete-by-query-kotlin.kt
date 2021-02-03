val searchQuery: Map<String, Any?> = HashMap<String, Any?>().apply {
  put("query", HashMap<String, Any?>().apply {
    put("match", HashMap<String, Any?>().apply {
      put("capacity", 4)
    })
  })
}

val result: ArrayList<String> = kuzzle
  .documentController
  .deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery)
  .get()
