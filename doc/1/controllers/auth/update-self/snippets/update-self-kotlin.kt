kuzzle.authController.login("local", HashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val result: Map<String, Any?> =
  kuzzle.authController.updateSelf(HashMap<String, Any?>().apply {
    put("age", 42)
  }).get();
