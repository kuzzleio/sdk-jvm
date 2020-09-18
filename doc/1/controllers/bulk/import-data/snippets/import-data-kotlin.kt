val bulkData: ArrayList<ConcurrentHashMap<String, Any?>> = ArrayList<ConcurrentHashMap<String, Any?>>().apply {
  add(ConcurrentHashMap<String, Any?>().apply {
    put("index", ConcurrentHashMap<String, Any?>());
  });
  add(ConcurrentHashMap<String, Any?>().apply {
    put("a", "document");
    put("with", "any");
    put("number", "of fields");
  });
  add(ConcurrentHashMap<String, Any?>().apply {
    put("create", 
      ConcurrentHashMap<String, Any?>().apply {
        put("_id", "uniq-id-1");
      }
    );
  });
  add(ConcurrentHashMap<String, Any?>().apply {
    put("another", "document");
  });
  add(ConcurrentHashMap<String, Any?>().apply {
    put("create", 
      ConcurrentHashMap<String, Any?>().apply {
        put("_id", "uniq-id-2");
      }
    );
  });
  add(ConcurrentHashMap<String, Any?>().apply {
    put("and", 
      ConcurrentHashMap<String, Any?>().apply {
        put("another", "one");
      }
    );
  });
};

val result: ConcurrentHashMap<String, Any?> =
  kuzzle.bulkController.importData(
    "nyc-open-data",
    "yellow-taxi",
    bulkData
  )
  .get()