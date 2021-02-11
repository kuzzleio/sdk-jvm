val startTime = Date(1234567890000)
val stopTime = Date(1541426610000)

val result: Map<String, Any?> = kuzzle
    .serverController
    .getStats(startTime, stopTime)
    .get()
