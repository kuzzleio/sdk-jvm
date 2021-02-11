Map<String, Object> term = new HashMap<String, Object>();
term.put("capacity", 7);

Map<String, Object> searchQuery = new HashMap<String, Object>();
searchQuery.put("query", term);

Integer result = kuzzle.getBulkController().deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery).get();
