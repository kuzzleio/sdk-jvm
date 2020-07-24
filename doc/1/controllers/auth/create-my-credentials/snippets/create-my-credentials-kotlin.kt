kuzzle.authController.login("local", ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

kuzzle.authController.createMyCredentials("other", ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo2")
  put("password", "bar2")
}).get()
