Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

Map<String, Object> response = kuzzle.getAuthController().login("local", credentials).get();
kuzzle.getAuthController().deleteMyCredentials("local").get();
