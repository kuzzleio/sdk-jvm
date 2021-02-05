Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

kuzzle.getAuthController().login("local", credentials).get();

Map<String, Object> custom = new HashMap<>();
custom.put("age", 42);

Map<String, Object> result =
  kuzzle.getAuthController().updateSelf(custom).get();
