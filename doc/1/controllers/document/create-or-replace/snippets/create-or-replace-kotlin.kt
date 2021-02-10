val document: Map<String, Any?> = HashMap<String, Any?>().apply {
  put("firstname", "John")
}

val result: Map<String, Any?> =
    kuzzle
        .documentController
        .createOrReplace("nyc-open-data", "yellow-taxi", "some-id", document)
        .get()
