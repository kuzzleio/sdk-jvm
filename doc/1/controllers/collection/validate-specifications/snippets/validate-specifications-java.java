    Map<String, Object> specifications = new HashMap<>();
    Map<String, Object> fields = new HashMap<>();
    Map<String, Object> license = new HashMap<>();

    specifications.put("strict", false);
    license.put("mandatory", true);
    license.put("type", "symbol"); // type 'symbol' is not a recognized type
    fields.put("license", license);
    specifications.put("fields", fields);

    Map<String, Object> result = kuzzle
        .getCollectionController()
        .validateSpecifications("nyc-open-data", "yellow-taxi", specifications)
        .get();

/*
  {
    valid=false,
    details=[
      "In nyc-open-data.yellow-taxi.license: symbol is not a recognized type."
    ],
    description="Some errors with provided specifications."
  }
*/