ConcurrentHashMap<String, Object> definition = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> mappings = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> license = new ConcurrentHashMap<>();

license.put("type", "keyword");
properties.put("license", license);
mappings.put("properties", properties);
definition.put("mappings", mappings);

kuzzle.getCollectionController().create("nyc-open-data", "yellow-taxi", definition)
  .get();