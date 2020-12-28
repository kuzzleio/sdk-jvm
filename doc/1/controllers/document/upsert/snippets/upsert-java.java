ConcurrentHashMap<String, Object> category = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> body = new ConcurrentHashMap<>();
category.put("category", "suv");
body.put("changes", category);

ConcurrentHashMap<String, Object> result = kuzzle
    .getDocumentController()
    .upsert("nyc-open-data", "yellow-taxi", "some-id", body)
    .get();

/*
{
  created=true
  _id="some_id",
  version=1
}
  */