val document: ConcurrentHashMap<String?, Any?> = ConcurrentHashMap<String?, Any?>().apply {
  put("name", "nina-vkote")
}

kuzzle.realtimeController.publish("my-index", "my-collection", document)