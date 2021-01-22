val document1: ConcurrentHashMap<String, Any?> =
    ConcurrentHashMap<String, Any?>().apply {
        put("_id", "some-id")
        put(
            "body",
            ConcurrentHashMap<String, Any?>().apply {
                put("name", "Smith")
            }
        )
    }

val document2: ConcurrentHashMap<String, Any?> =
    ConcurrentHashMap<String, Any?>().apply {
        put("_id", "some-id2")
        put(
            "body",
            ConcurrentHashMap<String, Any?>().apply {
                put("name", "Freeman")
            }
        )
    }

val documents: ArrayList<ConcurrentHashMap<String, Any?>> =
    ArrayList<ConcurrentHashMap<String, Any?>>().apply {
        add(document1)
        add(document2)
    }

val result: ConcurrentHashMap<String, ArrayList<Any>?> =
    kuzzle
        .documentController
        .mReplace("nyc-open-data", "yellow-taxi", documents)
        .get()
