val result: ConcurrentHashMap<String, Any?> = kuzzle
    .serverController
    .info()
    .get()
