Map<String, Object> filters = new HashMap<>();
Map<String, Object> range = new HashMap<>();
Map<String, Object> age = new HashMap<>();

age.put("lte", 20);
range.put("age", age);
filters.put("range", range);

kuzzle.getRealtimeController().subscribe(
  "nyc-open-data",
  "yellow-taxi",
  filters,
  "all",
  "all",
  notification -> {
    if (notification.getScope().equals("out")) {
      System.out.println("Document left the scope");
    } else {
      System.out.println("Document moved in the scope");
    }
  }).get();

Map document = new HashMap<>();
document.put("age", 19);
Map<String, Object> query = new HashMap<>();
query.put("controller", "document");
query.put("action", "create");
query.put("index", "nyc-open-data");
query.put("collection", "yellow-taxi");
query.put("_id", "nina-vkote");
query.put("body", document);
kuzzle.query(query).get();

query.put("action", "update");
document.put("age", 42);
kuzzle.query(query).get();