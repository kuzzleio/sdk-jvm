---
code: true
type: page
title: mWrite
description: Creates or replaces multiple documents directly into the storage engine.
---

# mWrite

This is a low level route intended to bypass Kuzzle actions on document creation, notably:
  - check [document validity](/core/2/guides/advanced/data-validation),
  - add [kuzzle metadata](/core/2/guides/main-concepts/data-storage#kuzzle-metadata),
  - trigger [realtime notifications](/core/2/guides/main-concepts/realtime-engine) (unless asked otherwise)

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> importData (
  String index,
  String collection,
  ArrayList<Map<String, Object>> bulkData
) throws NotConnectedException, InternalException
```

| Argument     | Type              | Description                                                                                                                      |
|--------------|-------------------|----------------------------------------------------------------------------------------------------------------------------------|
| `index`      | <pre>String</pre> | Index name                                                                                                                       |
| `collection` | <pre>String</pre> | Collection name                                                                                                                  |
| `documents`  | <pre>ArrayList<Map<String, Object>></pre> | An array of JObject representing the documents|

### documents

An array of `Map<String, Object>`. Each object describes a document to create or replace, by exposing the following properties:
  - `_id`: document unique identifier (optional)
  - `body`: document content

### Options

| Property         | Type                         | Description                                                                              |
|------------------|------------------------------|------------------------------------------------------------------------------------------|
| `waitForRefresh` | <pre>Bool</pre><br>(`false`) | If set to true, Kuzzle will not respond until the created/replaced documents are indexed |
| `notify`         | <pre>Bool</pre><br>(`false`) | If set to true, Kuzzle will trigger realtime notifications                               |

## Return

Returns a `Map<String, Object>` containing 2 arrays: `successes` and `errors`

Each created or replaced document is an object of the `successes` array with the following properties:

| Name      | Type              | Description                                            |
| --------- | ----------------- | ------------------------------------------------------ |
| `_id`      | <pre>String</pre> | Document ID                     |
| `_version` | <pre>Integer</pre> | Version of the document in the persistent data storage |
| `_source`  | <pre>Map<String, Object></pre> | Document content                                       |
| `created`  | <pre>Bool</pre> | True if the document was created |

Each errored document is an object of the `errors` array with the following properties:

| Name      | Type              | Description                                            |
| --------- | ----------------- | ------------------------------------------------------ |
| `document`  | <pre>Map<String, Object></pre> | Document that cause the error                                       |
| `status` | <pre>Integer</pre> | HTTP error status |
| `reason`  | <pre>String</pre> | Human readable reason |

## Usage

<<< ./snippets/mwrite-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun importData(
  index: String,
  collection: String,
  bulkData: ArrayList<Map<String, Any?>>
): CompletableFuture<Map<String, Any?>>
```

| Argument     | Type              | Description                                                                                                                      |
|--------------|-------------------|----------------------------------------------------------------------------------------------------------------------------------|
| `index`      | <pre>String</pre> | Index name                                                                                                                       |
| `collection` | <pre>String</pre> | Collection name                                                                                                                  |
| `documents`  | <pre>ArrayList<Map<String, Any?>></pre> | An array of JObject representing the documents|

### documents

An array of `Map<String, Any?>`. Each object describes a document to create or replace, by exposing the following properties:
  - `_id`: document unique identifier (optional)
  - `body`: document content

### Options

| Property         | Type                         | Description                                                                              |
|------------------|------------------------------|------------------------------------------------------------------------------------------|
| `waitForRefresh` | <pre>Bool</pre><br>(`false`) | If set to true, Kuzzle will not respond until the created/replaced documents are indexed |
| `notify`         | <pre>Bool</pre><br>(`false`) | If set to true, Kuzzle will trigger realtime notifications                               |

## Return

Returns a `Map<String, Object>` containing 2 arrays: `successes` and `errors`

Each created or replaced document is an object of the `successes` array with the following properties:

| Name      | Type              | Description                                            |
| --------- | ----------------- | ------------------------------------------------------ |
| `_id`      | <pre>String</pre> | Document ID                     |
| `_version` | <pre>Int</pre> | Version of the document in the persistent data storage |
| `_source`  | <pre><Map<String, Any?></pre> | Document content                                       |
| `created`  | <pre>Bool</pre> | True if the document was created |

Each errored document is an object of the `errors` array with the following properties:

| Name      | Type              | Description                                            |
| --------- | ----------------- | ------------------------------------------------------ |
| `document`  | <pre>Map<String, Any?></pre> | Document that cause the error                                       |
| `status` | <pre>Int</pre> | HTTP error status |
| `reason`  | <pre>String</pre> | Human readable reason |

## Usage

<<< ./snippets/mwrite-kotlin.kt

:::
::::
