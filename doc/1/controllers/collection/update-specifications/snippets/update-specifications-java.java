    Map<String, Object> specifications = new HashMap<>();
    Map<String, Object> fields = new HashMap<>();
    Map<String, Object> license = new HashMap<>();

    specifications.put("strict", false);
    license.put("mandatory", true);
    license.put("type", "string");
    fields.put("license", license);
    specifications.put("fields", fields);

    Map<String, Object> result = kuzzle
        .getCollectionController()
        .updateSpecifications("nyc-open-data", "yellow-taxi", specifications)
        .get();

/*
   {
    strict=false,
    fields={
      license={
        mandatory=true,
        type="string"
      }
      }
    }
 */
