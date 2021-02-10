---
code: true
type: page
title: update
description: Updates a document
---

# update

Updates a document.

---

:::: tabs
::: tab Java

## Arguments
 
```java
public CompletableFuture<Map<String, Object>> update(
      String index,
      String collection,
      String id,
      Map<String, Object> document,
      Boolean waitForRefresh,
      Integer retryOnConflict,
      Boolean source)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, Object>> update(
      String index,
      String collection,
      String id,
      Map<String, Object> document,
      Boolean waitForRefresh,
      Integer retryOnConflict)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, Object>> update(
      String index,
      String collection,
      String id,
      Map<String, Object> document,
      Boolean waitForRefresh)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, Object>> update(
      String index,
      String collection,
      String id,
      Map<String, Object> document)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id        `       | <pre>String</pre>                            | Document ID                        |
| `document`         | <pre>Map<String, Object></pre> | Partial document content |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|
| `retryOnConflict`  | <pre>Integer</pre>                           | The number of times the database layer should retry in case of version conflict |
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |

---

## Return

A `Map` which has the following properties:

| Property     | Type                         | Description                                                    |
|------------- |----------------------------- |--------------------------------------------------------------- |
| `_source`    | <pre>Map</pre> | Updated document (If source option set to true)                |
| `_id`        | <pre>String</pre>            | ID of the updated document                                     |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage         |

## Usage

<<< ./snippets/update-java.java

:::
::: tab Kotlin

## Arguments
 
```kotlin
  fun update(
      index: String,
      collection: String,
      id: String?,
      document: Map<String?, Any?>?,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null,
      source: Boolean? = null): CompletableFuture<Map<String, Any?>>
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id        `       | <pre>String</pre>                            | Document ID                        |
| `document`         | <pre>Map<String, Any?></pre> | Partial document content |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|
| `retryOnConflict`  | <pre>Int</pre>                           | The number of times the database layer should retry in case of version conflict |
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |

---

## Return

A `Map` which has the following properties:

| Property     | Type                         | Description                                                    |
|------------- |----------------------------- |--------------------------------------------------------------- |
| `_source`    | <pre>Map</pre> | Updated document (If source option set to true)                |
| `_id`        | <pre>String</pre>            | ID of the updated document                                     |
| `_version`   | <pre>Int</pre>           | Version of the document in the persistent data storage         |

## Usage

<<< ./snippets/update-kotlin.kt

:::
::::
