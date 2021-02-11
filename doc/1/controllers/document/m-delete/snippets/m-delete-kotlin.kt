val ids: ArrayList<String> = ArrayList<String>().apply {
    add("some-id")
    add("some-id2")
}

val result: Map<String, ArrayList<Any>> =
    kuzzle
        .documentController
        .mDelete("nyc-open-data", "yellow-taxi", ids)
        .get()
