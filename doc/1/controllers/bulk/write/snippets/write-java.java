ConcurrentHashMap<String, Object> content = new ConcurrentHashMap<String, Object>();
ConcurrentHashMap<String, Object> kuzzleInfo = new ConcurrentHashMap<String, Object>();
kuzzleInfo.put("author", "<kuid>");
kuzzleInfo.put("createdAd", "1481816934209");

content.put("_kuzzle_info", kuzzleInfo);

ConcurrentHashMap<String, Object> result = 
kuzzle.getBulkController().write("nyc-open-data", "yellow-taxi", content).get();