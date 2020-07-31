val match: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply {
  put("category", "suv")
}
val query: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply {
  put("match", match)
}

val searchQuery: ConcurrentHashMap<String, Any?> =
ConcurrentHashMap<String, Any?>().apply {
  put("query", query)
}
val result: SearchResult = kuzzle
.documentController
.search("nyc-open-data", "yellow-taxi", searchQuery, "1s", 0, 10).get();

    // Fetch the matched items by advancing through the result pages
    val matched: ArrayList<ConcurrentHashMap<String, Any>> = ArrayList<>();

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
    System.out.println("Successfully retrieved " + matched.size() + " documents");
