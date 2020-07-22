val suv: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("category", "suv")
  }

val limousine: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("category", "limousine")
  }


    CreateOptions options = new CreateOptions();
    options.setWaitForRefresh(true);

    for (int i = 0; i < 5; i += 1) {
      kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", suv, options).get();
    }

    for (int i = 0; i < 10; i += 1) {
      kuzzle.getDocumentController().create("nyc-open-data", "yellow-taxi", limousine, options).get();
    }

  val match: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("category", "suv")
  }
  val query: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("match", match)
  }

val searchQuery: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("query", query)
  }
  val results = kuzzle
        .getDocumentController()
        .search("nyc-open-data", "yellow-taxi", searchQuery).get();

    System.out.println("Successfully retrieved " + results.total + " documents");
