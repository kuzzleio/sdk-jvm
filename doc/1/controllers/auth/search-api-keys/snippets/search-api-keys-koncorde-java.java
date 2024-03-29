
Map<String, Object> description = new HashMap<>();
description.put("description", "LoRa permanent API Key");

Map<String, Object> query = new HashMap<>();
query.put("controller", "security");
query.put("action", "createApiKey");
query.put("userId", "jared.doe");
query.put("refresh", "wait_for");
query.put("_id", "lora-key");
query.put("body", description);

kuzzle.query(query).get();

description.put("description", "Sigfox API key");
query.put("_id", "sigfox-key");
query.put("body", description);
kuzzle.query(query).get();

description.put("description", "LoRa 6 month API key");
query.put("_id", "lora-temp-key");
query.put("body", description);
query.put("expiresIn", 36000);
kuzzle.query(query).get();

Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "jared.doe");
credentials.put("password", "password");

kuzzle.getAuthController().login("local", credentials).get();

Map<String, Object> equals = new HashMap<>();
equals.put("ttl", "36000");

Map<String, Object> squery = new HashMap<>();
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