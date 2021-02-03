ConcurrentHashMap<String, Object> searchQuery = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
ConcurrentHashMap<String, Object> equals = new ConcurrentHashMap<>();
equals.put("category", "suv");
query.put("equals", equals);
searchQuery.put("query", query);

SearchResult results = kuzzle
    .getDocumentController()
    .search("nyc-open-data", "yellow-taxi", searchQuery, Lang.KONCORDE).get();


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
