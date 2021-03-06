val document1: Map<String, Any?> =
    HashMap<String, Any?>().apply {
      put("_id", "some-id")
      put("body", HashMap<String, Any?>().apply {
        put("Agent", "Smith")
      })
    }

val document2: Map<String, Any?> =
    HashMap<String, Any?>().apply {
      put("_id", "some-id2")
      put("body", HashMap<String, Any?>().apply {
        put("Gordon", "Freeman")
      })
    }

val documents: ArrayList<Map<String, Any?>> =
    ArrayList<Map<String, Any?>>().apply {
      add(document1)
      add(document2)
    }

val result: Map<String, ArrayList<Any>> =
    kuzzle
        .documentController
        .mCreateOrReplace("nyc-open-data", "yellow-taxi", documents)
        .get()
