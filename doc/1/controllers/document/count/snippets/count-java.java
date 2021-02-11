Map<String, Object> searchQuery = new HashMap<>();
Map<String, Object> match = new HashMap<>();
match.put("Hello", "Clarisse");
searchQuery.put("match", match);

Integer result = kuzzle
  .getDocumentController()
  .count("nyc-open-data", "yellow-taxi", searchQuery)
  .get();