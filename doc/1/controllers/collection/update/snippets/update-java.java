ConcurrentHashMap<String, Object> definition = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> _meta = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> plate = new ConcurrentHashMap<>();

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