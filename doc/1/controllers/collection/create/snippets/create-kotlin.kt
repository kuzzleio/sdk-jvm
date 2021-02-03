val type: Map<String, Any> = Map<String, Any>().apply {
  put("type", "keyword")
}
val license: Map<String, Any> = Map<String, Any>().apply {
  put("license", type)
}
val mappings: Map<String, Any> = Map<String, Any>().apply {
  put("properties", license)
}
val definition: Map<String, Any> = Map<String, Any>().apply {
  put("mappings", mappings)
}
kuzzle.collectionController.create(
  "nyc-open-data",
  "yellow-taxi",
  definition
).get()
