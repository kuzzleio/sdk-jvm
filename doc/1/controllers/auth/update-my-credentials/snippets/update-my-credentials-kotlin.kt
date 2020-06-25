val credentials = ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}

kuzzle.authController.login("local", credentials).get()

val result: ConcurrentHashMap<String, Any?> =
  kuzzle.authController.updateMyCredentials("local", credentials).get();
