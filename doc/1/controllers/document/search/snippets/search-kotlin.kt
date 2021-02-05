val match: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("category", "suv")
  }
val query: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("match", match)
  }

val searchQuery: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("query", query)
  }
val results = kuzzle
  .documentController
  .search("nyc-open-data", "yellow-taxi", searchQuery).get();
