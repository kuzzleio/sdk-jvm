kuzzle.authController.login("local", ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

kuzzle.authController.logout()