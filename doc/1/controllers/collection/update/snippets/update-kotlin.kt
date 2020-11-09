val plate: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply{
  put("type", "keyword")
}

val _meta: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply{
  put("area", "Panipokhari")
}

val properties: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply{
  put("plate", plate)
}

val definition: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply{
  put("dynamic", false)
  put("_meta", _meta)
  put("properties", properties)
}

kuzzle
    .collectionController
    .update("nyc-open-data", "yellow-taxi", definition)
    .get();

print("Success");