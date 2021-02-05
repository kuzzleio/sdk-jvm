val result: Map<String, Any?> =
    kuzzle
        .documentController
        .get("nyc-open-data", "yellow-taxi", "some-id")
        .get()
