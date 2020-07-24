val result: ConcurrentHashMap<String, Any?> = kuzzle
    .serverController
    .getConfig()
    .get()