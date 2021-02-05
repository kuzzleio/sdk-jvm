Map<String, Object> credentials = new HashMap<>();
credentials.put("username", "foo");
credentials.put("password", "bar");

Map<String, Object> response =
  kuzzle.getAuthController().login("local", credentials).get();
Map<String, Object> responseToken =
  kuzzle.getAuthController().checkToken(response.get("jwt").toString()).get();
