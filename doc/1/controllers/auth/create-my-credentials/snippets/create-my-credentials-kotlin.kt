kuzzle.authController.login("local", Map<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

kuzzle.authController.createMyCredentials("other", Map<String, Any?>().apply {
  put("username", "foo2")
  put("password", "bar2")
}).get()
