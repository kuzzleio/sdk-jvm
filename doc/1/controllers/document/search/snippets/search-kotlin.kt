val match: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("category", "suv")
  }
val query: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("match", match)
  }

val searchQuery: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("query", query)
  }
val results = kuzzle
  .documentController
  .search("nyc-open-data", "yellow-taxi", searchQuery).get();

System.out.println("Successfully retrieved " + results.total + " documents");
