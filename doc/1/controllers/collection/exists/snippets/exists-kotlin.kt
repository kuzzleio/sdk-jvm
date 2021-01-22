val result = kuzzle
    .collectionController
    .exists("nyc-open-data", "yellow-taxi")
    .get()
