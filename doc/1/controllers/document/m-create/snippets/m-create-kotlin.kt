val document1: ConcurrentHashMap<String, Any?> =
    ConcurrentHashMap<String, Any?>().apply {
      put("_id", "some-id")
      put("body", ConcurrentHashMap<String, Any?>().apply {
        put("Agent", "Smith")
      })
    }

val document2: ConcurrentHashMap<String, Any?> =
    ConcurrentHashMap<String, Any?>().apply {
      put("_id", "some-id2")
      put("body", ConcurrentHashMap<String, Any?>().apply {
        put("Gordon", "Freeman")
      })
    }

val documents: ArrayList<ConcurrentHashMap<String, Any?>> =
    ArrayList<ConcurrentHashMap<String, Any?>>()

documents.add(document1)
documents.add(document2)

val result: ConcurrentHashMap<String, ArrayList<Any>> =
  kuzzle
  .documentController
  .mCreate("nyc-open-data", "yellow-taxi", documents)
  .get()