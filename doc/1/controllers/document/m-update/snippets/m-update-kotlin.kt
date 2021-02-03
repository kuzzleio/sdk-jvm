val document1: Map<String, Any?> = 
  Map<String, Any?>().apply {
    put("_id", "some-id")
    put("body", Map<String, Any?>().apply {
      put("name", "Smith")
    })
  }

val document2: Map<String, Any?> = 
  Map<String, Any?>().apply {
    put("_id", "some-id2")
    put("body", Map<String, Any?>().apply {
      put("name", "Freeman")
    })
  }

val documents: ArrayList<Map<String, Any?>> =
    ArrayList<Map<String, Any?>>().apply {
      add(document1)
      add(document2)
    }


val result: Map<String, ArrayList<Any>?> =
  kuzzle
  .documentController
  .mReplace("nyc-open-data", "yellow-taxi", documents)
  .get()