val document: Map<String?, Any?> = Map<String?, Any?>().apply {
  put("name", "nina-vkote")
}

kuzzle.realtimeController.publish("my-index", "my-collection", document)