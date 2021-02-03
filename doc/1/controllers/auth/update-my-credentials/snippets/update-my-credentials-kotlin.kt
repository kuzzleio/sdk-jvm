val credentials = Map<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}

kuzzle.authController.login("local", credentials).get()

val result: Map<String, Any?> =
  kuzzle.authController.updateMyCredentials("local", credentials).get();
