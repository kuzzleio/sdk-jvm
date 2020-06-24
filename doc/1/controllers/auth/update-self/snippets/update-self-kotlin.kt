kuzzle.authController.login("local", ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val result: ConcurrentHashMap<String, Any?> =
  kuzzle.authController.updateSelf(ConcurrentHashMap<String, Any?>().apply {
    put("age", 42)
  }).get();
