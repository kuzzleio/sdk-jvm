Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

kuzzle.getAuthController().login("local", credentials).get();
Boolean result = kuzzle.getAuthController().validateMyCredentials("local", credentials).get();
