Map<String, Object> definition = new HashMap<>();
Map<String, Object> mappings = new HashMap<>();
Map<String, Object> properties = new HashMap<>();
Map<String, Object> license = new HashMap<>();

license.put("type", "keyword");
properties.put("license", license);
mappings.put("properties", properties);
definition.put("mappings", mappings);

kuzzle.getCollectionController().create("nyc-open-data", "yellow-taxi", definition)
  .get();