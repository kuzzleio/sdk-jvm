Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

kuzzle.getAuthController().login("local", credentials).get();
Map<String, Object> result = kuzzle.getAuthController().updateMyCredentials("local", credentials).get();
