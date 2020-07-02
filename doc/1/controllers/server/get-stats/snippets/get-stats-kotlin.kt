val startTime = Timestamp(1234567890000);
val stopTime = Timestamp(1541426610000);

val result: ConcurrentHashMap<String, Any?> = kuzzle
    .serverController
    .getStats(startTime, stopTime)
    .get()