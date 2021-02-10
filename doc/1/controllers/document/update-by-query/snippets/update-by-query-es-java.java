Map<String, Object> searchQuery = new HashMap<>();
Map<String, Object> match = new HashMap<>();
match.put("capacity", 4);
searchQuery.put("match", match);

Map<String, Object> changes = new HashMap<>();
changes.put("capacity", 42);

Map<String, ArrayList<Object>> result = kuzzle
    .getDocumentController()
    .updateByQuery("nyc-open-data", "yellow-taxi", searchQuery, changes)
    .get();

/*
{
  successes=[
              {
                _id=<document-id>,
                _source=<updated document> // if source set to true
                status=200
              },
              {
                _id=<document id>,
                _source=<updated document> // if source set to true
                status=200
              }
            ],
    errors=[]
}
*/