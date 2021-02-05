val equals: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("category", "suv")
  }
val query: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("equals", equals)
  }

val searchQuery: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("query", query)
  }

val results = kuzzle
  .documentController
  .search("nyc-open-data", "yellow-taxi", searchQuery, lang = Lang.KONCORDE).get();

/*
{
  "aggregations"=undefined,
  "hits"=[
    {
      "_id"="AWgi6A1POQUM6ucJ3q06",
      "_score"=0.046520017,
      "_source"={
        "category"="suv",
        "_kuzzle_info"={
          "author"="-1",
          "createdAt"=1546773859655,
          "updatedAt"=null,
          "updater"=null
        }
      }
    },
    ...
  ]
},
"total"=5,
"fetched"=5,
"scroll_id"=undefined
*/
