  ConcurrentHashMap<String, Object> definition = new ConcurrentHashMap<>();
  ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<>();
  ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

  license.put("type", "keyword");
  properties.put("license", license);
  definition.put("properties", properties);

  kuzzle.getCollectionController().create("nyc-open-data", "yellow-taxi", definition)
    .get();