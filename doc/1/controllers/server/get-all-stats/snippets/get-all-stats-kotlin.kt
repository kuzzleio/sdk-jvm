val result: Map<String, Any?> = kuzzle
    .serverController
    .getAllStats()
    .get()
