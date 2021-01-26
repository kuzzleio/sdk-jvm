val license: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
    put("type", "symbol")
    put("mandatory", true)
}
val fields: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
    put("license", license)
}
val specifications: ConcurrentHashMap<String, Any> = ConcurrentHashMap<String, Any>().apply {
    put("strict", false)
    put("fields", fields)
}

val result = kuzzle
    .collectionController
    .validateSpecifications("nyc-open-data", "yellow-taxi", specifications)
    .get()
