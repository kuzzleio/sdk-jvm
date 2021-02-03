val result: Map<String, Any?> =
    kuzzle.authController.login("local", Map<String, Any?>().apply {
      put("username", "foo")
      put("password", "bar")
    }).get()