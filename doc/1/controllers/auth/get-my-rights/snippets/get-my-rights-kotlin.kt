kuzzle.authController.login("local", ConcurrentHashMap<String, Any?>().apply {
  put("username", "foo")
  put("password", "bar")
}).get()

val result: ArrayList<Any> =
  kuzzle.authController.getMyRights().get()
