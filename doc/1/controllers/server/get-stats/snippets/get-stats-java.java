Timestamp startTime = new Timestamp(1234567890);
Timestamp stopTime = new Timestamp(1541426610);

ConcurrentHashMap<String, Object> result = kuzzle
    .getServerController()
    .getStats(startTime, stopTime)
    .get();