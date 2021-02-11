val result: Map<String, Any?> = kuzzle
    .serverController
    .getConfig()
    .get()
