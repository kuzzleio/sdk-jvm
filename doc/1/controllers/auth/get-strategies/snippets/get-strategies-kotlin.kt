kuzzle.authController.login("local", Map<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val result: ArrayList<String> =
  kuzzle.authController.getStrategies().get();
