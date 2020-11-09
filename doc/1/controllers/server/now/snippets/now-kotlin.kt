val result: Date = kuzzle
    .serverController
    .now()
    .get()