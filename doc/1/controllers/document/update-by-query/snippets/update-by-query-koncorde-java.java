ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> equals = new ConcurrentHashMap<>();
equals.put("capacity", 4);
searchQuery.put("equals", equals);

ConcurrentHashMap<String, Object> changes = new ConcurrentHashMap<>();
changes.put("capacity", 42);

ConcurrentHashMap<String, ArrayList<Object>> result = kuzzle
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