val document: ConcurrentHashMap<String, Any?> =
    ConcurrentHashMap<String, Any?>().apply {
        put("name", "Johny")
    }

val result: ConcurrentHashMap<String, Any?> =
    kuzzle
        .documentController
        .update("nyc-open-data", "yellow-taxi", "some-id", document)
        .get()
