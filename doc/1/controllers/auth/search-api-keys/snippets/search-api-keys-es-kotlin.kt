
val description: ConcurrentHashMap<String?, Any?> = ConcurrentHashMap<String?, Any?>().apply {
  put("description", "LoRa permanent API Key")
}

val query: ConcurrentHashMap<String?, Any?> = ConcurrentHashMap<String?, Any?>().apply {
  put("controller", "security")
  put("action", "createApiKey")
  put("userId", "jared.doe")
  put("refresh", "wait_for")
  put("body", description)
}

kuzzle.query(query).get()

description.put("description", "Sigfox API key")
query.put("body", description);
kuzzle.query(query).get()

description.put("description", "LoRa 6 month API key")
query.put("body", description);
query.put("expiresIn", 36000);
kuzzle.query(query).get()

kuzzle.authController.login("local", ConcurrentHashMap<String, Any?>().apply {
      put("username", "jared.doe")
      put("password", "password")
    }).get()

val match: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("description", "LoRa")
  }

val squery: ConcurrentHashMap<String, Any?> =
  ConcurrentHashMap<String, Any?>().apply {
    put("match", match)
  }

val results = kuzzle
  .authController
  .searchApiKeys(squery).get();

print("Found ${results.total} API keys matching 'LoRa'");

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