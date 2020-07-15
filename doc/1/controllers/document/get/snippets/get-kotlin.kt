val result: ConcurrentHashMap<String, Any?> =
    kuzzle
        .documentController
        .get("nyc-open-data", "yellow-taxi", "some-id")
        .get()
