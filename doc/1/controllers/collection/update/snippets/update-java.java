Map<String, Object> definition = new HashMap<>();
Map<String, Object> properties = new HashMap<>();
Map<String, Object> _meta = new HashMap<>();
Map<String, Object> plate = new HashMap<>();

plate.put("type", "keyword");
_meta.put("area", "Panipokhari");
properties.put("plate", plate);
definition.put("dynamic", false);
definition.put("_meta", _meta);
definition.put("properties", properties);

kuzzle
    .getCollectionController()
    .update("nyc-open-data", "yellow-taxi", definition)
    .get();

System.out.println("Success");