val response = kuzzle.authController.login("local", ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val responseToken: ConcurrentHashMap<String, Any?> =
  kuzzle.authController.checkToken(response["jwt"].toString()).get()