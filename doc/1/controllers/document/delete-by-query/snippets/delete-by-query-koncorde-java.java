Map<String, Object> searchQuery = new HashMap<>();
Map<String, Object> query = new HashMap<>();
Map<String, Object> equals = new HashMap<>();
equals.put("capacity", 4);
query.put("equals", equals);
searchQuery.put("query", query);
ArrayList<String> result = kuzzle
  .getDocumentController()
  .deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery, Lang.KONCORDE)
  .get();
