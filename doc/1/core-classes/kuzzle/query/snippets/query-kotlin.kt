val query: Map<String?, Any?> = Map<String?, Any?>().apply {
  put("controller", "document")
  put("action", "create")
  put("index", "nyc-open-data")
  put("collection", "yellow-taxi")
  put("_id", "my-custom-document-id")
  put("refresh", "wait_for")
  put("body", Map<String?, Any?>().apply {
    put("trip_distance", 4.23)
    put("passenger_count", 2)
  })
}

val res: Response = kuzzle.query(query).get()
