val content: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>().apply {
    put(
        "_kuzzle_info",
        ConcurrentHashMap<String, Any?>().apply {
            put("author", "<kuid>")
            put("createdAd", "1481816934209")
        }
    )
}

val result: ConcurrentHashMap<String, Any?> =
    kuzzle.bulkController.write("nyc-open-data", "yellow-taxi", content).get()
