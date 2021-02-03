val bulkData: ArrayList<Map<String, Any?>> = ArrayList<Map<String, Any?>>().apply {
  add(Map<String, Any?>().apply {
    put("index", Map<String, Any?>());
  });
  add(Map<String, Any?>().apply {
    put("a", "document");
    put("with", "any");
    put("number", "of fields");
  });
  add(Map<String, Any?>().apply {
    put("create", 
      Map<String, Any?>().apply {
        put("_id", "uniq-id-1");
      }
    );
  });
  add(Map<String, Any?>().apply {
    put("another", "document");
  });
  add(Map<String, Any?>().apply {
    put("create", 
      Map<String, Any?>().apply {
        put("_id", "uniq-id-2");
      }
    );
  });
  add(Map<String, Any?>().apply {
    put("and", 
      Map<String, Any?>().apply {
        put("another", "one");
      }
    );
  });
};

val result: Map<String, Any?> =
  kuzzle.bulkController.importData(
    "nyc-open-data",
    "yellow-taxi",
    bulkData
  )
  .get()