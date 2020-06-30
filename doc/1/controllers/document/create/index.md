---
code: true
type: page
title: create
description: Creates a new document
---

# create

Creates a new document in the persistent data storage.

Throws an error if the document already exists.

The optional parameter `waitForRefresh` can be used with the value `true` in order to wait for the document to be indexed (indexed documents are available for `search`).

---

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> document)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> document,
      String id)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> create(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> document,
      String id,
      Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `document`         | <pre>ConcurrentHashMap<String, Object></pre> | Document content |
| `id`               | <pre>String</pre> (optional)                 | Document identifier. Auto-generated if not specified              |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Created document                 |
| `_id`        | <pre>String</pre>            | ID of the newly created document                       |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage |

## Usage

<<< ./snippets/create-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun create(
  index: String,
  collection: String,
  document: ConcurrentHashMap<String, Any?>,
  id: String? = null,
  waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `document`         | <pre>ConcurrentHashMap<String, Any?></pre> | Document content |
| `id`               | <pre>String</pre> (optional)                 | Document identifier. Auto-generated if not specified              |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Created document                 |
| `_id`        | <pre>String</pre>            | ID of the newly created document                       |
| `_version`   | <pre>Int</pre>           | Version of the document in the persistent data storage |

## Usage

<<< ./snippets/create-kotlin.kt

:::
::::