ConcurrentHashMap<String, Object> filters = new ConcurrentHashMap<>();
filters.put("exists", "name");

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

final Integer result = kuzzle.getRealtimeController().count(roomId).get();