    Map<String, Object> content = new HashMap<>();
    content.put("name", "Johny");

    Map<String, Object> result =
    kuzzle.getDocumentController().update("nyc-open-data", "yellow-taxi", "some-id", content)
    .get();

/*
    response =
    {
        _id=some-id,
        _version=2
    }
*/