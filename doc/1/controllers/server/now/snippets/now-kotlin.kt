val result: Timestamp = kuzzle
    .serverController
    .now()
    .get()