val response = kuzzle.authController.login("local", Map<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val responseToken: Map<String, Any?> =
  kuzzle.authController.checkToken(response["jwt"].toString()).get()