
ConcurrentHashMap<String, Object> description = new ConcurrentHashMap<>();
description.put("description", "LoRa permanent API Key");

ConcurrentHashMap<String, Object> query = new ConcurrentHashMap<>();
query.put("controller", "security");
query.put("action", "createApiKey");
query.put("userId", "jared.doe");
query.put("refresh", "wait_for");
query.put("body", description);

kuzzle.query(query).get();

description.put("description", "Sigfox API key");
query.put("body", description);
kuzzle.query(query).get();

description.put("description", "LoRa 6 month API key");
query.put("body", description);
query.put("expiresIn", 36000);
kuzzle.query(query).get();

ConcurrentHashMap<String, Object> credentials = new ConcurrentHashMap<>();
credentials.put("username", "jared.doe");
credentials.put("password", "password");

kuzzle.getAuthController().login("local", credentials).get();

ConcurrentHashMap<String, Object> equals = new ConcurrentHashMap<>();
equals.put("ttl", "36000");

ConcurrentHashMap<String, Object> squery = new ConcurrentHashMap<>();
squery.put("equals", equals);

SearchResult results = kuzzle
  .getAuthController()
  .searchApiKeys(squery, Lang.KONCORDE).get();

String output = String.format("Found %d API key", results.total);

System.out.println(output);

/*
{
  "total"=2,
  "hits"=[
    {
      "_id"="zXEwbG8BJASM_0-bWU-q",
      "_source"={
        "description"="LoRa 6 month API key",
        "userId"="jared.doe",
        "expiresAt"=31557600000,
        "fingerprint": "4ee98cb8c614e99213e7695f822e42325d86c93cfaf39cb40e860939e784c8e6",
        "ttl"=360000
      }
    }
  ]
}
*/