val result: ConcurrentHashMap<String, Any?> = kuzzle
    .serverController
    .getAllStats()
    .get()