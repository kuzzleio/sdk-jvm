val bulkData: ArrayList<Map<String, Any?>> = ArrayList<Map<String, Any?>>().apply {
  add(HashMap<String, Any?>().apply {
    put("index", HashMap<String, Any?>());
  });
  add(HashMap<String, Any?>().apply {
    put("a", "document");
    put("with", "any");
    put("number", "of fields");
  });
  add(HashMap<String, Any?>().apply {
    put("create", 
      HashMap<String, Any?>().apply {
        put("_id", "uniq-id-1");
      }
    );
  });
  add(HashMap<String, Any?>().apply {
    put("another", "document");
  });
  add(HashMap<String, Any?>().apply {
    put("create", 
      HashMap<String, Any?>().apply {
        put("_id", "uniq-id-2");
      }
    );
  });
  add(HashMap<String, Any?>().apply {
    put("and", 
      HashMap<String, Any?>().apply {
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
