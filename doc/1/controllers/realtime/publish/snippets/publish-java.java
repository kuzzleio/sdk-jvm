Map<String, Object> document = new HashMap<>();
document.put("name", "nina-vkote");

kuzzle.getRealtimeController().publish("my-index", "my-collection", document);