---
code: true
type: page
title: mReplace
description: Replaces multiple documents
---

# mReplace

Replaces multiple documents.

---

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, ArrayList<Object>>> mReplace(
      String index,
      String collection,
      ArrayList<Map<String, Object>> documents)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, ArrayList<Object>>> mReplace(
      String index,
      String collection,
      ArrayList<Map<String, Object>> documents,
      Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index                             |
| `collection`       | <pre>String</pre>                                       | Collection                        |
| `documents`        | <pre>ArrayList<Map<String, Object>></pre> | ArrayList containing the documents to replace |
| `waitForRefresh`   | <pre>Boolean</pre>                                      | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |

---

### documents

Each document has the following properties:

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `_id`              | <pre>String</pre>                            | Optional document ID. Will be auto-generated if not defined.             |
| `body`             | <pre>Map<String, Object></pre> | Document body |

## Return

A `Map<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Object></pre> | Created document                 |
| `_id`        | <pre>String</pre>                            | ID of the replaced document      |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `document`   | <pre>Map<String, Object></pre> | Document that causes the error   |
| `status`     | <pre>Integer</pre>                           | HTTP error status                |
| `reason`     | <pre>String</pre>                            | Human readable reason |

## Usage

<<< ./snippets/m-replace-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun mReplace(
      index: String,
      collection: String,
      documents: ArrayList<Map<String, Any?>>,
      waitForRefresh: Boolean? = null): CompletableFuture<Map<String, ArrayList<Any>?>>
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index                             |
| `collection`       | <pre>String</pre>                                       | Collection                        |
| `documents`        | <pre>ArrayList<Map<String, Any?>></pre> | ArrayList containing the documents to replace |
| `waitForRefresh`   | <pre>Boolean</pre>                                      | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |

---

### documents

Each document has the following properties:

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `_id`              | <pre>String</pre>                            | Optional document ID. Will be auto-generated if not defined.             |
| `body`             | <pre>Map<String, Any?></pre> | Document body |

## Return

A `Map<String, ArrayList<Any?>>` which has a `successes` and `errors` `ArrayList<Any?>`:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Any?></pre> | Created document                 |
| `_id`        | <pre>String</pre>                            | ID of the replaced document      |
| `_version`   | <pre>Int</pre>                           | Version of the document in the persistent data storage |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `document`   | <pre>Map<String, Any?></pre> | Document that causes the error   |
| `status`     | <pre>Int</pre>                           | HTTP error status                |
| `reason`     | <pre>String</pre>                            | Human readable reason |

## Usage

<<< ./snippets/m-replace-kotlin.kt

:::
::::
