
val license: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply {
  put("mandatory", true);
  put("type", "string");
};
val fields: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply {
  put("license", license);
};
val specifications: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply{
  put("strict", false);
  put("fields", fields);
};
val result: ConcurrentHashMap<String, Any?> = kuzzle
    .collectionController
    .updateSpecifications("nyc-open-data", "yellow-taxi", specifications)
    .get();

/*
   {
    strict=false,
    fields={
      license={
        mandatory=true,
        type="string"
      }
      }
    }
 */
