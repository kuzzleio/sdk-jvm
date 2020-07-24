val result: ConcurrentHashMap<String, Any?> = kuzzle
    .serverController
    .getLastStats()
    .get()