val result = kuzzle
    .collectionController
    .getSpecifications("nyc-open-data", "yellow-taxi")
    .get();

/*
  {
    collection="yellow-taxi",
    index="nyc-open-data",
    validation={
      fields={
        age={
          defaultValue=42,
          mandatory=true,
          type="integer"
        }
      },
      strict=true
    }
  }
*/