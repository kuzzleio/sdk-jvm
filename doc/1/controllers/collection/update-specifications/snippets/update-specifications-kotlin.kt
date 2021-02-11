
val license: Map<String, Any?> =
HashMap<String, Any?>().apply {
  put("mandatory", true);
  put("type", "string");
};
val fields: Map<String, Any?> =
HashMap<String, Any?>().apply {
  put("license", license);
};
val specifications: Map<String, Any?> =
HashMap<String, Any?>().apply{
  put("strict", false);
  put("fields", fields);
};
val result: Map<String, Any?> = kuzzle
    .collectionController
    .updateSpecifications("nyc-open-data", "yellow-taxi", specifications)
    .get()

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
