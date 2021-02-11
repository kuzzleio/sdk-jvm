Map<String, Object> body = new HashMap<>();
body.put("name", "Melis");

Map<String, Object> requestPayload = new HashMap<>();
requestPayload.put("controller", "document");
requestPayload.put("action", "create");
requestPayload.put("index", "nyc-open-data");
requestPayload.put("collection", "yellow-taxi");
requestPayload.put("body", body);

Boolean result =
  kuzzle.getAuthController().checkRights(requestPayload).get();
