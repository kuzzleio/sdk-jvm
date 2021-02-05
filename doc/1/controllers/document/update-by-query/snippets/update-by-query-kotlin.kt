val match: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("capacity", 4)
  }

val searchQuery: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("match", match)  
  }

val changes: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("capacity", 42)
  }


val result: Map<String, ArrayList<Any?>> =
  kuzzle
  .documentController
  .updateByQuery("nyc-open-data", "yellow-taxi", searchQuery, changes)
  .get()
