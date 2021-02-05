Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

Map<String, Object> newCredentials = new HashMap<>();
newCredentials.put("username", "foo2");
newCredentials.put("password", "bar2");

Map<String, Object> response =
  kuzzle.getAuthController().login("local", credentials).get();
kuzzle.getAuthController().createMyCredentials("other", newCredentials).get();
