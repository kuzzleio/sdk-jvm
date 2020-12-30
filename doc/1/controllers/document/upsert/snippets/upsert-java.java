ConcurrentHashMap<String, Object> category = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> changes = new ConcurrentHashMap<>();
category.put("category", "suv");
changes.put("changes", category);

ConcurrentHashMap<String, Object> result = kuzzle
    .getDocumentController()
    .upsert("nyc-open-data", "yellow-taxi", "some-id", changes)
    .get();

/*
{
  created=true
  _id="some_id",
  version=1
}
  */