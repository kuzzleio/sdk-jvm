
  Map<String, Object> document = new HashMap<>();
  document.put("firstname", "John");

  Map<String, Object> result = kuzzle.getDocumentController().replace("nyc-open-data", "yellow-taxi", "some-id", document)
  .get();

  /*
  result =
      {
          _source=
              {
                firstname=John,
                _kuzzle_info={ createdAt=1582892606555, author=-1, updatedAt=1582892606555, updater=-1 }
              },
          _id=some-id,
          _version=2
      }
   */