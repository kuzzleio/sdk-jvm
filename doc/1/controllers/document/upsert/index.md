---
code: true
type: page
title: upsert
description: Applies partial changes to a document. If the document doesn't already exist, a new document is created.
---

# upsert

<SinceBadge version="Kuzzle 2.8.0"/>
<SinceBadge version="1.2.0" />

Applies partial changes to a document. If the document doesn't already exist, a new document is created.

:::: tabs
::: tab Java

```java
  public CompletableFuture<Map<String, Object>> upsert(
    String index,
    String collection,
    Map<String, Object> changes) throws NotConnectedException, InternalException

  public CompletableFuture<Map<String, Object>> upsert(
    String index,
    String collection,
    Map<String, Object> changes,
    Map<String, Object> defaults) throws NotConnectedException, InternalException

  public CompletableFuture<Map<String, Object>> upsert(
    String index,
    String collection,
    Map<String, Object> changes,
    Map<String, Object> defaults,
    Boolean waitForRefresh) throws NotConnectedException, InternalException
  
  public CompletableFuture<Map<String, Object>> upsert(
    String index,
    String collection,
    Map<String, Object> changes,
    Map<String, Object> defaults,
    Boolean waitForRefresh,
    Integer retryOnConflict) throws NotConnectedException, InternalException
  
  public CompletableFuture<Map<String, Object>> upsert(
    String index,
    String collection,
    Map<String, Object> changes,
    Map<String, Object> defaults,
    Boolean waitForRefresh,
    Integer retryOnConflict,
    Boolean source) throws NotConnectedException, InternalException
```

| Argument     | Type              | Description                               |
| ------------ | ----------------- | ----------------------------------------- |
| `index`      | <pre>String</pre> | Index name                                |
| `collection` | <pre>String</pre> | Collection name                           |
| `id`         | <pre>String</pre> | Document ID                               |
| `changes`       | <pre>Map<String, Object></pre> | Partial content of the document to update |

### Options

Additional query options

| Options           | Type<br/>(default)              | Description                                                                        |
| ----------------- | ------------------------------- | ---------------------------------------------------------------------------------- |
| `defaults` | <pre>Map<String, Object><br/>(`{}`)        | Fields to add to the document if it gets created    |
| `waitForRefresh`         | <pre>Boolean</pre><br/>(`""`)    | If set to `true`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>Integer</pre><br/>(`10`)        | The number of times the database layer should retry in case of version conflict    |
| `source`          | <pre>Boolean</pre><br/>(`false`)| If true, returns the updated document inside the response


## Returns

A `Map<String, Object>` with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Object></pre> | Updated document (if `source` option set to true)  |
| `_id`        | <pre>String</pre>                            | ID of the updated document                   |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |
| `created`     | <pre>Boolean</pre>    

## Usage

<<< ./snippets/upsert-java.java

:::
::: tab Kotlin

```kotlin
fun upsert(
  index: String,
  collection: String,
  id: String,
  changes: Map<String, Any?>,
  defaults: Map<String, Any?>,
  waitForRefresh: Boolean? = null,
  retryOnConflict: Int? = null,
  source: Boolean? = null): CompletableFuture<Map<String, Any?>>
```

| Argument     | Type              | Description                               |
| ------------ | ----------------- | ----------------------------------------- |
| `index`      | <pre>String</pre> | Index name                                |
| `collection` | <pre>String</pre> | Collection name                           |
| `id`         | <pre>String</pre> | Document ID                               |
| `changes`       | <pre>Map<String, Any?></pre> | Partial content of the document to update |

### Options

Additional query options

| Options           | Type<br/>(default)              | Description                                                                        |
| ----------------- | ------------------------------- | ---------------------------------------------------------------------------------- |
| `defaults` | <pre>Map<String, Any?><br/>(`{}`)        | Fields to add to the document if it gets created    |
| `waitForRefresh`         | <pre>Boolean</pre><br/>(`""`)    | If set to `true`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>Integer</pre><br/>(`10`)        | The number of times the database layer should retry in case of version conflict    |
| `source`          | <pre>Boolean</pre><br/>(`false`)| If true, returns the updated document inside the response


## Returns

A `Map<String, Any?>` object, with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Any?></pre> | Updated document (if `source` option set to true)  |
| `_id`        | <pre>String</pre>                            | ID of the updated document                   |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |
| `created`     | <pre>Boolean</pre>    

## Usage

<<< ./snippets/upsert-kotlin.kt

:::
::::
