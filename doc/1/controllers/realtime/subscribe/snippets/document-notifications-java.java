Map<String, Object> filters = new HashMap<>();
filters.put("exists", "name");

Map<String, Object> document = new HashMap<>();
document.put("name", "nina-vkote");

final String roomId = kuzzle.getRealtimeController().subscribe(
  "nyc-open-data",
  "yellow-taxi",
  filters,
  notification -> {
    if (notification.getScope().equals("in")) {
      System.out.println("Document entered the scope");
    } else {
      System.out.println("Document left the scope");
    }
}).get();

Map<String, Object> query = new HashMap<>();
query.put("controller", "document");
query.put("action", "create");
query.put("index", "nyc-open-data");
query.put("collection", "yellow-taxi");
query.put("_id", "nina-vkote");
query.put("body", document);
kuzzle.query(query).get();