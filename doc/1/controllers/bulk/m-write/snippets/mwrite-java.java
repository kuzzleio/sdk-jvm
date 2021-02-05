Map<String, Object> document = new HashMap<String, Object>();
ArrayList<Map<String, Object>> documents = new ArrayList<Map<String, Object>>();
documents.add(document);
document.put("_id", "foo");
document.put("body", new HashMap<String, Object>());

Map<String, Object> result =
kuzzle.getBulkController().mWrite("nyc-open-data", "yellow-taxi", documents).get();