ConcurrentHashMap<String, Object> term = new ConcurrentHashMap<String, Object>();
term.put("capacity", 7);

ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<String, Object>();
searchQuery.put("query", term);

Integer result = kuzzle.getBulkController().deleteByQuery("nyc-open-data", "yellow-taxi", searchQuery).get();
