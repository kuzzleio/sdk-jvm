Date startTime = new Date(1234567890);
Date stopTime = new Date(1541426610);

ConcurrentHashMap<String, Object> result = kuzzle
    .getServerController()
    .getStats(startTime, stopTime)
    .get();