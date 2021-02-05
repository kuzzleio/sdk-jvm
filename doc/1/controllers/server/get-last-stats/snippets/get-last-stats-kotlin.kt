val result: Map<String, Any?> = kuzzle
    .serverController
    .getLastStats()
    .get()