val result: Boolean = kuzzle
  .documentController
  .exists("nyc-open-data", "yellow-taxi", "some-id")
  .get()
