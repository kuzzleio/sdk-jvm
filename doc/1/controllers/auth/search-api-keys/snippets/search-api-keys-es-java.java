
Map<String, Object> description = new HashMap<>();
description.put("description", "LoRa permanent API Key");

Map<String, Object> query = new HashMap<>();
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

Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "jared.doe");
credentials.put("password", "password");

kuzzle.getAuthController().login("local", credentials).get();

Map<String, Object> match = new HashMap<>();
match.put("description", "LoRa");

Map<String, Object> squery = new HashMap<>();
squery.put("match", match);

SearchResult results = kuzzle
  .getAuthController()
  .searchApiKeys(squery).get();

String output = String.format("Found %d API keys matching 'LoRa'", results.total);

System.out.println(output);

/*
{
  "total"=2,
  "hits"=[
    {
      "_id"="znEwbG8BJASM_0-bWU-q",
      "_source"={
        "description"="LoRa permanent API key",
        "userId"="jared.doe",
        "expiresAt"=-1,
        "fingerprint": "4ee98cb8c614e99213e7695f822e42325d86c93cfaf39cb40e860939e784c8e6",
        "ttl"=-1
      }
    },
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