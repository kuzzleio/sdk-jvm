let ids: ArrayList<String> ids = ArrayList<String>().apply {
    add("some-id")
    add("some-id2")
}

let result: ConcurrentHashMap<String, ArrayList<Any>> =
    kuzzle
    .documentController
    .mGet("nyc-open-data", "yellow-taxi", ids)
    .get()
