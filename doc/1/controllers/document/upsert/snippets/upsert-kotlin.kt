val category: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("category", "suv")
  }

val changes: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("changes", category)  
  }

val result: ConcurrentHashMap<String, Any?> =
  kuzzle
  .documentController
  .upsert("nyc-open-data", "yellow-taxi", "some-id", changes)
  .get()
