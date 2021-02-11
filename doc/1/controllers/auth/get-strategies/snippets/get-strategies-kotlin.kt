kuzzle.authController.login("local", HashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val result: ArrayList<String> =
  kuzzle.authController.getStrategies().get();
