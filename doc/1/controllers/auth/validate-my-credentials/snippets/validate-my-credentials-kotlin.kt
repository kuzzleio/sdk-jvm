val credentials = HashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}

kuzzle.authController.login("local", credentials).get()

val result: Boolean = kuzzle.authController.validateMyCredentials("local", credentials).get();
