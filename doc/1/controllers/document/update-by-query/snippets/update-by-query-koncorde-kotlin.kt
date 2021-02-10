val equals: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("capacity", 4)
  }

val searchQuery: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("equals", equals)  
  }

val changes: Map<String, Any?> =
  HashMap<String, Any?>().apply {
    put("capacity", 42)
  }


val result: Map<String, ArrayList<Any?>> =
  kuzzle
  .documentController
  .updateByQuery("nyc-open-data", "yellow-taxi", searchQuery, changes, lang = Lang.KONCORDE)
  .get()

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
