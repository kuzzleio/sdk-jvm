---
code: true
type: page
title: updateByQuery
description: Updates documents matching query
---

# updateByQuery

Updates documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<SinceBadge version="change-me"/>

This method also supports the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) to match documents by passing the `lang` argument with the value `koncorde`.  
Koncorde filters will be translated into an Elasticsearch query.  

::: warning
Koncorde `bool` operator and `regexp` clause are not supported for search queries.
:::

An empty or null query will match all documents in the collection.

<br/>

:::: tabs
::: tab Java


```java
    public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      ConcurrentHashMap<String, Object> changes) throws NotConnectedException, InternalException

    public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      ConcurrentHashMap<String, Object> changes,
      Lang lang) throws NotConnectedException, InternalException

    public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      ConcurrentHashMap<String, Object> changes,
      Boolean waitForRefresh) throws NotConnectedException, InternalException
    
    public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      ConcurrentHashMap<String, Object> changes,
      Boolean waitForRefresh,
      Integer retryOnConflict) throws NotConnectedException, InternalException

    public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      ConcurrentHashMap<String, Object> changes,
      Boolean waitForRefresh,
      Integer retryOnConflict,
      Boolean source) throws NotConnectedException, InternalException

    public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> updateByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      ConcurrentHashMap<String, Object> changes,
      Boolean waitForRefresh,
      Integer retryOnConflict,
      Boolean source,
      Lang lang) throws NotConnectedException, InternalException
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Object></pre> | Query to match  |
| `changes`          | <pre>ConcurrentHashMap<String, Object></pre> | Partial changes to apply to the documents |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |
| `retryOnConflict`  | <pre>Integer</pre> (optional)                | The number of times the database layer should retry in case of version conflict |
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |
| `lang`     | <pre>[Lang](/sdk/jvm/1/core-classes/lang)</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |

---

## Returns

A `ConcurrentHashMap<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
Each updated document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap<String, Object></pre> | Updated document (if `source` option set to true)  |
| `_id`        | <pre>String</pre>                            | ID of the udated document                   |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |
| `status`     | <pre>Integer</pre>                           | HTTP status code |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `document`   | <pre>ConcurrentHashMap<String, Object></pre> | Document that causes the error   |
| `status`     | <pre>Integer</pre>                           | HTTP error status                |
| `reason`     | <pre>String</pre>                            | Human readable reason |

## Usage

With the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<<< ./snippets/update-by-query-es-java.java

With the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.


<<< ./snippets/update-by-query-koncorde-java.java

:::
::: tab Kotlin

```kotlin
fun updateByQuery(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?>,
      changes: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null,
      retryOnConflict: Int? = null,
      source: Boolean? = null,
      lang: Lang = Lang.ELASTICSEARCH): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any?>>>
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Any?></pre> | Query to match  |
| `changes`          | <pre>ConcurrentHashMap<String, Any?></pre> | Partial changes to apply to the documents |
| `waitForRefresh`   | <pre>Boolean</pre>                           | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|
| `retryOnConflict`  | <pre>Int</pre> (optional)                | The number of times the database layer should retry in case of version conflict |
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |
| `lang`     | <pre>[Lang](/sdk/jvm/1/core-classes/lang)</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |

---

## Returns

A `ConcurrentHashMap<String, ArrayList<Any?>>` which has a `successes` and `errors` `ArrayList<Any?>`:
Each updated document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>ConcurrentHashMap<String, Any?></pre> | Updated document (if `source` option set to true)  |
| `_id`        | <pre>String</pre>                            | ID of the udated document                   |
| `_version`   | <pre>Int</pre>                           | Version of the document in the persistent data storage |
| `status`     | <pre>Int</pre>                           | HTTP status code |

Each errored document is an object of the `errors` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `document`   | <pre>ConcurrentHashMap<String, Any?></pre> | Document that causes the error   |
| `status`     | <pre>Int</pre>                           | HTTP error status                |
| `reason`     | <pre>String</pre>                            | Human readable reason |

## Usage

With the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<<< ./snippets/update-by-query-es-kotlin.kt

With the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.


<<< ./snippets/update-by-query-koncorde-kotlin.kt

:::
::::
