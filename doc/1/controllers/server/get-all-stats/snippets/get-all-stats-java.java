ConcurrentHashMap<String, Object> result = kuzzle
    .getServerController()
    .getAllStats()
    .get();