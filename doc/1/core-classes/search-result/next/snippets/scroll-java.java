    Map<String, Object> searchQuery = new HashMap<>();
    Map<String, Object> query = new HashMap<>();
    Map<String, Object> match = new HashMap<>();
    match.put("category", "suv");
    query.put("match", match);
    searchQuery.put("query", query);

    SearchResult results = kuzzle.getDocumentController().search(
      "nyc-open-data",
      "yellow-taxi",
      searchQuery, "1s", 10).get();

    // Fetch the matched items by advancing through the result pages
    ArrayList<Map<String, Object>> matched = new ArrayList<>();

    while (results != null) {
      matched.addAll(results.hits);
      results = results.next().get();
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
