val type: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
  put("type", "keyword")
}
val license: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
  put("license", type)
}
val definition: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
  put("properties", license)
}

kuzzle.collectionController.create(
  "nyc-open-data",
  "yellow-taxi",
  definition
).get()
