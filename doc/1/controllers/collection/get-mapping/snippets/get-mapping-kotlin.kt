val result = kuzzle
    .collectionController
    .getMapping("nyc-open-data", "yellow-taxi")
    .get()

/*
{
  _meta={
      schema={},
      allowForm=false
    },
    dynamic=true,
    properties={
      key={
        type=text
        }
      }
    }
*/
