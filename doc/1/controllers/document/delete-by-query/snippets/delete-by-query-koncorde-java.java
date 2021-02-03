ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> equals = new ConcurrentHashMap<>();
equals.put("capacity", 4);
query.put("equals", equals);
searchQuery.put("query", query);
ArrayList<String> result = kuzzle
  .getDocumentController()
  .deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery, Lang.KONCORDE)
  .get();
