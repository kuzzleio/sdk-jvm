val license: Map<String, Any> = HashMap<String, Any>().apply {
  put("type", "symbol")
  put("mandatory", true)
}
val fields: Map<String, Any> = HashMap<String, Any>().apply {
  put("license", license)
}
val specifications: Map<String, Any> = HashMap<String, Any>().apply {
  put("strict", false)
  put("fields", fields)
}

val result = kuzzle
    .collectionController
    .validateSpecifications("nyc-open-data", "yellow-taxi", specifications)
    .get()
