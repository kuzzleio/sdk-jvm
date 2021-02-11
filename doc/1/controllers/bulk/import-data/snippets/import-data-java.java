
Map<String, Object> index = new HashMap<String, Object>();
index.put("index", new HashMap<String, Object>());

Map<String, Object> document = new HashMap<String, Object>();
document.put("a", "document");
document.put("with", "any");
document.put("number", "of fields");

Map<String, Object> id = new HashMap<String, Object>();
id.put("_id", "uniq-id-1");
Map<String, Object> create = new HashMap<String, Object>();
create.put("create", id);

Map<String, Object> document2 = new HashMap<String, Object>();
document2.put("another", "document");

Map<String, Object> id2 = new HashMap<String, Object>();
id2.put("_id", "uniq-id-2");
Map<String, Object> create2 = new HashMap<String, Object>();
create2.put("create", id2);

Map<String, Object> another = new HashMap<String, Object>();
id2.put("another", "one");
Map<String, Object> and = new HashMap<String, Object>();
create2.put("and", another);

ArrayList<Map<String, Object>> bulkData = new ArrayList<Map<String, Object>>();
bulkData.add(index);
bulkData.add(document);
bulkData.add(create);
bulkData.add(document2);
bulkData.add(create2);
bulkData.add(and);

Map<String, Object> result =
  kuzzle.getBulkController().importData(
    "nyc-open-data",
    "yellow-taxi",
    bulkData
  )
  .get();

