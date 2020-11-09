kuzzle.authController.login("local", ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val result: ConcurrentHashMap<String, Any?> =
  kuzzle.authController.getCurrentUser().get()
