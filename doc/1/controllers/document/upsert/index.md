---
code: true
type: page
title: upsert
---

# upsert

<SinceBadge version="Kuzzle 2.8.0"/>
<SinceBadge version="auto-version" />

Applies partial changes to a document. If the document doesn't already exist, a new document is created.

:::: tabs
::: tab Java

```java
  public CompletableFuture<ConcurrentHashMap<String, Object>> upsert(
    String index,
    String collection,
    ConcurrentHashMap<String, Object> body) throws NotConnectedException, InternalException

  public CompletableFuture<ConcurrentHashMap<String, Object>> upsert(
    String index,
    String collection,
    ConcurrentHashMap<String, Object> body,
    Boolean waitForRefresh) throws NotConnectedException, InternalException
  
  public CompletableFuture<ConcurrentHashMap<String, Object>> upsert(
    String index,
    String collection,
    ConcurrentHashMap<String, Object> body,
    Boolean waitForRefresh,
    Integer retryOnConflict) throws NotConnectedException, InternalException
  
  public CompletableFuture<ConcurrentHashMap<String, Object>> upsert(
    String index,
    String collection,
    ConcurrentHashMap<String, Object> body,
    Boolean waitForRefresh,
    Integer retryOnConflict,
    Boolean source) throws NotConnectedException, InternalException
```

| Argument     | Type              | Description                               |
| ------------ | ----------------- | ----------------------------------------- |
| `index`      | <pre>String</pre> | Index name                                |
| `collection` | <pre>String</pre> | Collection name                           |
| `id`         | <pre>String</pre> | Document ID                               |
| `body`       | <pre>ConcurrentHashMap<String, Object></pre> | Partial content of the document to update and fields to add to the document if it gets created |

### Body properties


| Argument           | Type<br/>(default)              | Description                                                                        |
| ----------------- | ------------------------------- | ---------------------------------------------------------------------------------- |
| `changes`         | <pre>ConcurrentHashMap<String, Object></pre>    | partial changes to apply to the document |
| `defaults` | <pre>ConcurrentHashMap<String, Object><br/>(`{}`)        | (optional) fields to add to the document if it gets created    |

### Options

Additional query options

| Options           | Type<br/>(default)              | Description                                                                        |
| ----------------- | ------------------------------- | ---------------------------------------------------------------------------------- |
| `waitForRefresh`         | <pre>Boolean</pre><br/>(`""`)    | If set to `true`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>Integer</pre><br/>(`10`)        | The number of times the database layer should retry in case of version conflict    |
| `source`          | <pre>Boolean</pre><br/>(`false`)| If true, returns the updated document inside the response


## Returns

A `ConcurrentHashMap<String, Object>` with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap<String, Object></pre> | Updated document (if `source` option set to true)  |
| `_id`        | <pre>String</pre>                            | ID of the udated document                   |
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
      body: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null,
      source: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

| Argument     | Type              | Description                               |
| ------------ | ----------------- | ----------------------------------------- |
| `index`      | <pre>String</pre> | Index name                                |
| `collection` | <pre>String</pre> | Collection name                           |
| `id`         | <pre>String</pre> | Document ID                               |
| `body`       | <pre>ConcurrentHashMap<String, Any?></pre> | Partial content of the document to update and fields to add to the document if it gets created |

### Body properties


| Argument           | Type<br/>(default)              | Description                                                                        |
| ----------------- | ------------------------------- | ---------------------------------------------------------------------------------- |
| `changes`         | <pre>ConcurrentHashMap<String, Any?></pre>    | partial changes to apply to the document |
| `defaults` | <pre>ConcurrentHashMap<String, Any?><br/>(`{}`)        | (optional) fields to add to the document if it gets created    |

### Options

Additional query options

| Options           | Type<br/>(default)              | Description                                                                        |
| ----------------- | ------------------------------- | ---------------------------------------------------------------------------------- |
| `waitForRefresh`         | <pre>Boolean</pre><br/>(`""`)    | If set to `true`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>Integer</pre><br/>(`10`)        | The number of times the database layer should retry in case of version conflict    |
| `source`          | <pre>Boolean</pre><br/>(`false`)| If true, returns the updated document inside the response


## Returns

A `ConcurrentHashMap<String, Any?>` with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap<String, Any?></pre> | Updated document (if `source` option set to true)  |
| `_id`        | <pre>String</pre>                            | ID of the udated document                   |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |
| `created`     | <pre>Boolean</pre>    

## Usage

<<< ./snippets/upsert-kotlin.kt

:::
::::