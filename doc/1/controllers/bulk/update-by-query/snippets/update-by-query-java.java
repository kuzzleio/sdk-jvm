Map<String, Object> term = new HashMap<String, Object>();
term.put("capacity", 7);

Map<String, Object> searchQuery = new HashMap<String, Object>();
searchQuery.put("query", term);

Map<String, Object> changes = new HashMap<String, Object>();
term.put("capacity", 3);

Map<String, Object> changesQuery = new HashMap<String, Object>();
searchQuery.put("changes", changes);

Integer result = kuzzle.getBulkController().updateByQuery("nyc-open-data", "yellow-taxi", searchQuery, changesQuery).get();
