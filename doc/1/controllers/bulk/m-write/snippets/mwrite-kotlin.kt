val documents: ArrayList<ConcurrentHashMap<String, Any?>> = ArrayList<ConcurrentHashMap<String, Any?>>().apply {
    add(
        ConcurrentHashMap<String, Any?>().apply {
            put("_id", "foo")
            put("body", ConcurrentHashMap<String, Any?>())
        }
    )
}

val result: ConcurrentHashMap<String, Any?> =
    kuzzle.bulkController.mWrite("nyc-open-data", "yellow-taxi", documents).get()
