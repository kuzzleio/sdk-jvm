val body = HashMap<String, Any?>().apply {
    put("name", "Melis")
}

val requestPayload = HashMap<String, Any?>().apply {
    put("controller", "document")
    put("action", "create")
    put("index", "nyc-open-data")
    put("collection", "yellow-taxi")
    put("body", body)
}

val result = kuzzle.authController.checkRights(requestPayload).get()
