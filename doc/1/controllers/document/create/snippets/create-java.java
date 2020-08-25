ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
document.put("firstname", "John");

ConcurrentHashMap<String, Object> result =
    kuzzle
      .getDocumentController()
      .create("nyc-open-data", "yellow-taxi", document)
      .get();