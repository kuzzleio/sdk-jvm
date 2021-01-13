ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();
body.put("name", "Melis");

ConcurrentHashMap<String, Object> requestPayload = new ConcurrentHashMap<>();
requestPayload.put("controller", "document");
requestPayload.put("action", "create");
requestPayload.put("index", "nyc-open-data");
requestPayload.put("collection", "yellow-taxi");
requestPayload.put("body", body);

Boolean result =
  kuzzle.getAuthController().checkRights(requestPayload).get();
