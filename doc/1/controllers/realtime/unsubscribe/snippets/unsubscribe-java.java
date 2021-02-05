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

kuzzle.getRealtimeController().unsubscribe(roomId).get();