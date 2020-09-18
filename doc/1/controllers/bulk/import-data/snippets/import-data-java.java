
ConcurrentHashMap<String, Object> index = new ConcurrentHashMap<String, Object>();
index.put("index", new ConcurrentHashMap<String, Object>());

ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<String, Object>();
document.put("a", "document");
document.put("with", "any");
document.put("number", "of fields");

ConcurrentHashMap<String, Object> id = new ConcurrentHashMap<String, Object>();
id.put("_id", "uniq-id-1");
ConcurrentHashMap<String, Object> create = new ConcurrentHashMap<String, Object>();
create.put("create", id);

ConcurrentHashMap<String, Object> document2 = new ConcurrentHashMap<String, Object>();
document2.put("another", "document");

ConcurrentHashMap<String, Object> id2 = new ConcurrentHashMap<String, Object>();
id2.put("_id", "uniq-id-2");
ConcurrentHashMap<String, Object> create2 = new ConcurrentHashMap<String, Object>();
create2.put("create", id2);

ConcurrentHashMap<String, Object> another = new ConcurrentHashMap<String, Object>();
id2.put("another", "one");
ConcurrentHashMap<String, Object> and = new ConcurrentHashMap<String, Object>();
create2.put("and", another);

ArrayList<ConcurrentHashMap<String, Object>> bulkData = new ArrayList<ConcurrentHashMap<String, Object>>();
bulkData.add(index);
bulkData.add(document);
bulkData.add(create);
bulkData.add(document2);
bulkData.add(create2);
bulkData.add(and);

ConcurrentHashMap<String, Object> result =
  kuzzle.getBulkController().importData(
    "nyc-open-data",
    "yellow-taxi",
    bulkData
  )
  .get();

