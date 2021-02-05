kuzzle.authController.login("local", HashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val result: Boolean = kuzzle.authController.credentialsExist("local").get()
