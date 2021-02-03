val match: Map<String, Any?> =
Map<String, Any?>().apply {
  put("category", "suv")
}
val query: Map<String, Any?> =
Map<String, Any?>().apply {
  put("match", match)
}

val searchQuery: Map<String, Any?> =
Map<String, Any?>().apply {
  put("query", query)
}
var result: SearchResult? = kuzzle
  .documentController
  .search("nyc-open-data", "yellow-taxi", searchQuery, "1s", 10).get();

    // Fetch the matched items by advancing through the result pages
    val matched: ArrayList<Map<String, Any>> = ArrayList<Map<String, Any>>();

    while (result != null) {
      matched.addAll(result.hits);
      result = result.next().get();
    }

  /*
    { _id="suv_no1",
      _score=0.03390155,
      _source=
       { _kuzzle_info=
          { author="-1",
            updater=null,
            updatedAt=null,
            createdAt=1570093133057 },
         category="suv" } }
  */