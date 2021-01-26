val searchQuery: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>().apply {
    put(
        "query",
        ConcurrentHashMap<String, Any?>().apply {
            put(
                "match",
                ConcurrentHashMap<String, Any?>().apply {
                    put("capacity", 4)
                }
            )
        }
    )
}

val result: ArrayList<String> = kuzzle
    .documentController
    .deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery)
    .get()
