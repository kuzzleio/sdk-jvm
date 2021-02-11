Map<String, Object> searchQuery = new HashMap<>();
Map<String, Object> query = new HashMap<>();
Map<String, Object> match = new HashMap<>();
match.put("capacity", 4);
query.put("match", match);
searchQuery.put("query", query);
ArrayList<String> result = kuzzle
  .getDocumentController()
  .deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery)
  .get();
