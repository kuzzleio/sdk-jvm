---
code: true
type: page
title: mUpdate
description: Updates multiple documents
---

# mUpdate

Updates multiple documents.

---

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, ArrayList<Object>>> mUpdate(
      String index,
      String collection,
      ArrayList<Map<String, Object>> documents)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, ArrayList<Object>>> mUpdate(
      String index,
      String collection,
      ArrayList<Map<String, Object>> documents,
      Boolean waitForRefresh)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, ArrayList<Object>>> mUpdate(
      String index,
      String collection,
      ArrayList<Map<String, Object>> documents,
      Boolean waitForRefresh,
      Integer retryOnConflict)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index                             |
| `collection`       | <pre>String</pre>                                       | Collection                        |
| `documents`        | <pre>ArrayList<Map<String, Object>></pre> | ArrayList containing the documents to update |
| `retryOnConflict`  | <pre>Integer</pre> (optional)                | The number of times the database layer should retry in case of version conflict |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |

---

### documents

Each document has the following properties:

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `_id`              | <pre>String</pre>                            | Document ID             |
| `body`             | <pre>Map<String, Object></pre> | Document body |


## Return

A `Map<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Object></pre> | Updated document                 |
| `_id`        | <pre>String</pre>                            | ID of the updated document       |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `document`   | <pre>Map<String, Object></pre> | Document that causes the error   |
| `status`     | <pre>Integer</pre>                           | HTTP error status                |
| `reason`     | <pre>String</pre>                            | Human readable reason |

## Usage

<<< ./snippets/m-update-java.java


:::
::: tab Kotlin

## Arguments

```kotlin
  fun mUpdate(
      index: String,
      collection: String,
      documents: ArrayList<Map<String, Any?>?>,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null): CompletableFuture<Map<String, ArrayList<Any>?>>
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index                             |
| `collection`       | <pre>String</pre>                                       | Collection                        |
| `documents`        | <pre>ArrayList<Map<String, Any?>></pre> | ArrayList containing the documents to update |
| `retryOnConflict`  | <pre>Int</pre> (optional)                | The number of times the database layer should retry in case of version conflict |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |

---

### documents

Each document has the following properties:

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `_id`              | <pre>String</pre>                            | Document ID             |
| `body`             | <pre>Map<String, Any?></pre> | Document body |


## Return

A `Map<String, ArrayList<Any?>>` which has a `successes` and `errors` `ArrayList<Any?>`:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Any?></pre> | Updated document                 |
| `_id`        | <pre>String</pre>                            | ID of the updated document       |
| `_version`   | <pre>Int</pre>                           | Version of the document in the persistent data storage |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `document`   | <pre>Map<String, Any?></pre> | Document that causes the error   |
| `status`     | <pre>Int</pre>                           | HTTP error status                |
| `reason`     | <pre>String</pre>                            | Human readable reason |

## Usage

<<< ./snippets/m-update-kotlin.kt

:::
::::
