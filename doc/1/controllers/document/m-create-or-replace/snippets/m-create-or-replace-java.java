Map<String, Object> document1 = new HashMap<>();
Map<String, Object> document2 = new HashMap<>();
Map<String, Object> body = new HashMap<>();
Map<String, Object> body2 = new HashMap<>();

body.put("Agent", "Smith");
body2.put("Gordon", "Freeman");

document1.put("_id", "some-id");
document1.put("body", body);

document2.put("_id", "some-id2");
document2.put("body", body2);

final ArrayList<Map<String, Object>> documents = new ArrayList<>();
documents.add(document1);
documents.add(document2);

Map<String, ArrayList<Object>> result = kuzzle
  .getDocumentController()
  .mCreateOrReplace("nyc-open-data", "yellow-taxi", documents)
  .get();

/*
  result =
  {
    successes=
    [
      {
        result=created,
        _source=
          {
            Agent=Smith,
            _kuzzle_info={createdAt=1582892842099, author=-1}
          },
        _id=some-id,
        _version=1,
        status=201
      },
      {
        result=created,
        _source=
          {
            Gordon=Freeman,
            _kuzzle_info={createdAt=1582892842099, author=-1}
          },
        _id=some-id2,
        _version=1,
        status=201
      }
    ],
    errors=[]
  }
*/
