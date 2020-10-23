ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<String, Object>();
ArrayList<ConcurrentHashMap<String, Object>> documents = new ArrayList<ConcurrentHashMap<String, Object>>();
documents.add(document);
document.put("_id", "foo");
document.put("body", new ConcurrentHashMap<String, Object>());

ConcurrentHashMap<String, Object> result = 
kuzzle.getBulkController().mWrite("nyc-open-data", "yellow-taxi", documents).get();