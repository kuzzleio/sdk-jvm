Map<String, Object> category = new HashMap<>();
Map<String, Object> changes = new HashMap<>();
category.put("category", "suv");
changes.put("changes", category);

Map<String, Object> result = kuzzle
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