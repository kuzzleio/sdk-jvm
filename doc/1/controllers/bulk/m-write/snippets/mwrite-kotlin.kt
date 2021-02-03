val documents: ArrayList<Map<String, Any?>> = ArrayList<Map<String, Any?>>().apply {
  add(Map<String, Any?>().apply {
    put("_id", "foo")
    put("body", Map<String, Any?>())
  })
};

val result: Map<String, Any?> = 
kuzzle.bulkController.mWrite("nyc-open-data", "yellow-taxi", documents).get();