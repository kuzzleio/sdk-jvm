val body = ConcurrentHashMap<String, Any?>().apply {
  put("name", "Melis")
}

val requestPayload = ConcurrentHashMap<String, Any?>().apply {
  put("controller", "document")
  put("action", "create")
  put("index", "nyc-open-data")
  put("collection", "yellow-taxi")
  put("body", body)
}

val result = kuzzle.authController.checkRights("foo", requestPayload).get()