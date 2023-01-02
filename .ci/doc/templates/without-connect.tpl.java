try {
  AbstractProtocol protocol;
  if (System.getenv("SNIPPET_PROTOCOL") != null && System.getenv("SNIPPET_PROTOCOL").equals("http")) {
    protocol = new Http("kuzzle");
  } else {
    protocol = new WebSocket("kuzzle");
  }
  kuzzle = new Kuzzle(protocol);
  [snippet-code]
  System.out.println("Success");
} catch (Exception e) {
  System.err.println(e.getMessage());
} finally {
  if (kuzzle != null) {
    kuzzle.disconnect();
  }
}
