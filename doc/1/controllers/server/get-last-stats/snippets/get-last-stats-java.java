ConcurrentHashMap<String, Object> result = kuzzle
    .getServerController()
    .getLastStats()
    .get();