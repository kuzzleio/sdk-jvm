Map<String, Object> credentials = new HashMap<>();
  credentials.put("username", "foo");
  credentials.put("password", "bar");

Map<String, Object> result =
  kuzzle.getAuthController().login("local", credentials).get();
