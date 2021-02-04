val document: Map<String?, Any?> = HashMap<String?, Any?>().apply {
  put("name", "nina-vkote")
}

kuzzle.realtimeController.publish("my-index", "my-collection", document)