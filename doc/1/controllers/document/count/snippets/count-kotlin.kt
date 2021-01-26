val searchQuery: ConcurrentHashMap<String, Any?> =
    ConcurrentHashMap<String, Any?>().apply {
        put(
            "match",
            ConcurrentHashMap<String, Any?>().apply {
                put("Hello", "Clarisse")
            }
        )
    }

val result: Int = kuzzle
    .documentController
    .count("nyc-open-data", "yellow-taxi", searchQuery)
    .get()
