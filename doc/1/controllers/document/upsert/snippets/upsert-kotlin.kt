val category: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("category", "suv")
  }

val changes: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("changes", category)  
  }

val result: Map<String, Any?> =
  kuzzle
    .documentController
    .upsert("nyc-open-data", "yellow-taxi", "some-id", changes)
    .get()
