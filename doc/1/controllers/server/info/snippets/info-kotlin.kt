val result: Map<String, Any?> = kuzzle
    .serverController
    .info()
    .get()
