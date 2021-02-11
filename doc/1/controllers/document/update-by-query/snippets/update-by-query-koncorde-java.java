Map<String, Object> searchQuery = new HashMap<>();
Map<String, Object> equals = new HashMap<>();
equals.put("capacity", 4);
searchQuery.put("equals", equals);

Map<String, Object> changes = new HashMap<>();
changes.put("capacity", 42);

Map<String, ArrayList<Object>> result = kuzzle
    .getDocumentController()
    .updateByQuery("nyc-open-data", "yellow-taxi", searchQuery, changes, Lang.KONCORDE)
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
