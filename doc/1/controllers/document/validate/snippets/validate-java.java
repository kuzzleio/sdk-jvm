  Map<String, Object> document = new HashMap<>();
  document.put("key", "value");

  Boolean result = kuzzle
  .getDocumentController()
  .validate("nyc-open-data", "yellow-taxi", document)
  .get();
