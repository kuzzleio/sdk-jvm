try {
  AbstractProtocol protocol;
  if (System.getenv("SNIPPET_PROTOCOL") != null && System.getenv("SNIPPET_PROTOCOL").equals("http")) {
    protocol = new Http("kuzzle");
  } else {
    protocol = new WebSocket("kuzzle");
  }
  kuzzle = new Kuzzle(protocol);
  kuzzle.connect();
  [snippet-code]
  for (Object o : result.get("successes")) {
    System.out.println(o);
  }
} catch (Exception e) {
  e.printStackTrace();
} finally {
  if (kuzzle != null) {
    kuzzle.disconnect();
  }
}