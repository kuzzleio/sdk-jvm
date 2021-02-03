val args: Map<String, Any?> = Map<String, Any?>()
val filters: Map<String, Any?> =
Map<String, Any?>().apply {
  put("match_all", args)
}
val searchQuery: Map<String, Any?> =
Map<String, Any?>().apply {
  put("query", filters)
}

val result: SearchResult = kuzzle
    .collectionController
    .searchSpecifications(searchQuery, "10s", 0, 50).get();

print("fetched: " + result.fetched);
  /*
    {
      "total"=1,
      "hits"=[
        {
          "_index"="%kuzzle",
          "_type"="validations",
          "_id"="nyc-open-data#yellow-taxi",
          "_score"=1,
          "_source"={
            "validation"={
              "strict"=false,
              "fields"={
                "license"={
                  "type"="string"
                }
              }
            },
            "index"="nyc-open-data",
            "collection"="yellow-taxi"
          }
        }
      ],
      "scrollId"="DnF1ZXJ5VGhlbkZldGNoBQAAAAAAAACSFlgtZTJFYjNiU1FxQzhSNUFpNlZHZGcAAAAAAAAAkxZYLWUyRWIzYlNRcUM4UjVBaTZWR2RnAAAAAAAAAJQWWC1lMkViM2JTUXFDOFI1QWk2VkdkZwAAAAAAAACVFlgtZTJFYjNiU1FxQzhSNUFpNlZHZGcAAAAAAAAAlhZYLWUyRWIzYlNRcUM4UjVBaTZWR2Rn"
    }
  */