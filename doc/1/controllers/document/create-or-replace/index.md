---
code: true
type: page
title: createOrReplace
description: Creates or replaces a document
---

# createOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

---

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> createOrReplace(
      String index,
      String collection,
      String id,
      Map<String, Object> document)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, Object>> createOrReplace(
      String index,
      String collection,
      String id,
      Map<String, Object> document,
      Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id`               | <pre>String</pre>                            | Document ID                       |
| `document`         | <pre>Map<String, Object></pre> | Document content                  |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `Map` which has the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>Map</pre> | Document content                 |
| `_id`        | <pre>String</pre>            | ID of the document                       |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage |

## Usage

<<< ./snippets/create-or-replace-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun createOrReplace(
  index: String,
  collection: String,
  id: String,
  document: Map<String, Any?>,
  waitForRefresh: Boolean? = null): CompletableFuture<Map<String, Any?>>
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id`               | <pre>String</pre>                            | Document ID                       |
| `document`         | <pre>Map<String, Any?></pre> | Document content                  |
| `waitForRefresh`   | <pre>Boolean</pre><br>(`null`)               | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `Map` which has the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>Map</pre> | Document content                 |
| `_id`        | <pre>String</pre>            | ID of the document                       |
| `_version`   | <pre>Int</pre>           | Version of the document in the persistent data storage |

## Usage

<<< ./snippets/create-or-replace-kotlin.kt

:::
::::
