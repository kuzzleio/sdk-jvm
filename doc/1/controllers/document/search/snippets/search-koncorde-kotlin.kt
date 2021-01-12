val equals: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("category", "suv")
  }
val query: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("equals", equals)
  }

val searchQuery: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("query", query)
  }

val results = kuzzle
  .documentController
  .search("nyc-open-data", "yellow-taxi", searchQuery, lang = Lang.KONCORDE).get();
