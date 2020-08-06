---
code: true
type: page
title: Write
description: Creates or replaces a document directly into the storage engine.
---

# Write

Create or replace a document directly into the storage engine.

:::: tabs
::: tab Java

## Arguments


## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> write(
  String index,
  String collection,
  ConcurrentHashMap<String, Object> content,
  String id,
  Bool notify,
  Bool waitForRefresh
) throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> write(
  String index,
  String collection,
  ConcurrentHashMap<String, Object> content,
  String id,
  Bool notify
) throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> write(
  String index,
  String collection,
  ConcurrentHashMap<String, Object> content,
  String id
) throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, Object>> write(
  String index,
  String collection,
  ConcurrentHashMap<String, Object> content
) throws NotConnectedException, InternalException
```

| Argument     | Type               | Description                 |
|--------------|--------------------|-----------------------------|
| `index`      | <pre>String</pre>  | Index name                  |
| `collection` | <pre>String</pre>  | Collection name             |
| `content`    | <pre>ConcurrentHashMap<String, Object></pre> | Document content to create. |
| `id`         | <pre>String</pre><br>(`null`) | set the document unique ID to the provided value, instead of auto-generating a random ID |
| `waitForRefresh` | <pre>Bool</pre><br>(`false`)  | If set to true, Kuzzle will not respond until the created/replaced documents are indexed |
| `notify`         | <pre>Bool</pre><br>(`false`)  | If set to true, Kuzzle will trigger realtime notifications                               |

## Return

Return a ConcurrentHashMap<String, Object> with the following properties:

| Property   | Type               | Description                                     |
|------------|--------------------|-------------------------------------------------|
| `_id`      | <pre>String</pre>  | Created document unique identifier.             |
| `_source`  | <pre>ConcurrentHashMap<String, Object></pre> | Document content.                               |
| `_version` | <pre>Int</pre>     | Version number of the document                  |

## Usage

<<< ./snippets/write-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun write(
  index: String,
  collection: String,
  content: ConcurrentHashMap<String, Any?>,
  id: String? = null,
  notify: Boolean? = null,
  waitForRefresh: Boolean? = null):
  CompletableFuture<ConcurrentHashMap<String, Any?>>
```

| Argument     | Type               | Description                 |
|--------------|--------------------|-----------------------------|
| `index`      | <pre>String</pre>  | Index name                  |
| `collection` | <pre>String</pre>  | Collection name             |
| `content`    | <pre>ConcurrentHashMap<String, Any?></pre> | Document content to create. |


### Options

| Property         | Type                          | Description                                                                              |
|------------------|-------------------------------|------------------------------------------------------------------------------------------|
| `id`     | <pre>String</pre><br>(`null`) | set the document unique ID to the provided value, instead of auto-generating a random ID |
| `waitForRefresh` | <pre>Boolean</pre><br>(`false`)  | If set to true, Kuzzle will not respond until the created/replaced documents are indexed |
| `notify`         | <pre>Boolean</pre><br>(`false`)  | If set to true, Kuzzle will trigger realtime notifications                               |

## Return

Return a ConcurrentHashMap<Strin, Any?> with the following properties:

| Property   | Type               | Description                                     |
|------------|--------------------|-------------------------------------------------|
| `_id`      | <pre>String</pre>  | Created document unique identifier.             |
| `_source`  | <pre>ConcurrentHashMap<String, Any?></pre> | Document content.                               |
| `_version` | <pre>Int</pre>     | Version number of the document                  |

## Usage

<<< ./snippets/write-kotlin.kt

:::
::::