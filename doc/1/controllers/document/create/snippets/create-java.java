Map<String, Object> document = new HashMap<>();
document.put("firstname", "John");

Map<String, Object> result =
    kuzzle
      .getDocumentController()
      .create("nyc-open-data", "yellow-taxi", document)
      .get();