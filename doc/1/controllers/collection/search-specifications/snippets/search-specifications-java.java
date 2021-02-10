
Map<String, Object> searchQuery = new HashMap<>();
Map<String, Object> filters = new HashMap<>();
Map<String, Object> args = new HashMap<>();

filters.put("match_all", args);
searchQuery.put("query", filters);

SearchResult result = kuzzle
    .getCollectionController()
    .searchSpecifications(searchQuery, "10s", 0, 50).get();

System.out.println("fetched: " + result.fetched);
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