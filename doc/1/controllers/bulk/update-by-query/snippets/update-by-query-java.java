Map<String, Object> term = new HashMap<String, Object>();
term.put("capacity", 7);

Map<String, Object> searchQuery = new HashMap<String, Object>();
searchQuery.put("query", term);

Map<String, Object> changes = new HashMap<String, Object>();
changes.put("capacity", 3);
searchQuery.put("changes", changes);

Integer result = kuzzle.getBulkController().updateByQuery("nyc-open-data", "yellow-taxi", searchQuery, changes).get();
