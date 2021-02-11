val content: Map<String, Any?> = HashMap<String, Any?>().apply {
  put("_kuzzle_info", HashMap<String, Any?>().apply {
    put("author", "<kuid>")
    put("createdAd", "1481816934209")
  });
}

val result: Map<String, Any?> =
kuzzle.bulkController.write("nyc-open-data", "yellow-taxi", content).get();
