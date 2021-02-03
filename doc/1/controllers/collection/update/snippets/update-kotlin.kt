val plate: Map<String, Any?> =
Map<String, Any?>().apply{
  put("type", "keyword")
}

val _meta: Map<String, Any?> =
Map<String, Any?>().apply{
  put("area", "Panipokhari")
}

val properties: Map<String, Any?> =
Map<String, Any?>().apply{
  put("plate", plate)
}

val definition: Map<String, Any?> =
Map<String, Any?>().apply{
  put("dynamic", false)
  put("_meta", _meta)
  put("properties", properties)
}

kuzzle
    .collectionController
    .update("nyc-open-data", "yellow-taxi", definition)
    .get();

print("Success");