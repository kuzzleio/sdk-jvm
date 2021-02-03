Map<String, Object> content = new HashMap<String, Object>();
Map<String, Object> kuzzleInfo = new HashMap<String, Object>();
kuzzleInfo.put("author", "<kuid>");
kuzzleInfo.put("createdAd", "1481816934209");

content.put("_kuzzle_info", kuzzleInfo);

Map<String, Object> result =
kuzzle.getBulkController().write("nyc-open-data", "yellow-taxi", content).get();