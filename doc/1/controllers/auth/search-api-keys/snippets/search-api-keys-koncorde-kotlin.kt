
val description: HashMap<String?, Any?> = HashMap<String?, Any?>().apply {
  put("description", "LoRa permanent API Key")
}

val query: HashMap<String?, Any?> = HashMap<String?, Any?>().apply {
  put("controller", "security")
  put("action", "createApiKey")
  put("userId", "jared.doe")
  put("refresh", "wait_for")
  put("_id", "lora-key")
  put("body", description)
}

kuzzle.query(query).get()

description.put("description", "Sigfox API key")
query.put("_id", "sigfox-key")
query.put("body", description);
kuzzle.query(query).get()

description.put("description", "LoRa 6 month API key")
query.put("_id", "lora-temp-key")
query.put("body", description);
query.put("expiresIn", 36000);
kuzzle.query(query).get()

kuzzle.authController.login("local", HashMap<String, Any?>().apply {
      put("username", "jared.doe")
      put("password", "password")
    }).get()

val equals: HashMap<String, Any?> =
  HashMap<String, Any?>().apply {
    put("ttl", "36000")
  }

val squery: HashMap<String, Any?> =
  HashMap<String, Any?>().apply {
    put("equals", equals)
  }

val results = kuzzle
  .authController
  .searchApiKeys(squery, lang = Lang.KONCORDE).get();

print("Found ${results.total} API key");

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
