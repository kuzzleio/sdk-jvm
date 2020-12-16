---
code: true
type: page
title: deleteByQuery
description: Delete documents matching query
---

# deleteByQuery

Deletes documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<SinceBadge version="change-me"/>

This method also supports the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) to match documents by passing the `lang` argument with the value `koncorde`.  
Koncorde filters will be translated into an Elasticsearch query.  

::: warning
Koncorde `bool` operator and `regexp` clause are not supported for search queries.
:::

An empty or null query will match all documents in the collection.

:::: tabs
::: tab Java

## Arguments

```java
  public CompletableFuture<ArrayList<String>> deleteByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery) throws NotConnectedException, InternalException

  public CompletableFuture<ArrayList<String>> deleteByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      Boolean waitForRefresh) throws NotConnectedException, InternalException

  public CompletableFuture<ArrayList<String>> deleteByQuery(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      Boolean waitForRefresh,
      String lang) throws NotConnectedException, InternalException
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Object></pre> | Query to match  |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |
| `lang`     | <pre>String</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |

---

## Returns

Returns an `ArrayList<String>` containing the deleted document ids.

## Usage

<<< ./snippets/delete-by-query-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun deleteByQuery(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?>,
      waitForRefresh: Boolean? = null,
      lang: String? = null): CompletableFuture<ArrayList<String>>
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Any?></pre> | Query to match  |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |
| `lang`     | <pre>String</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |

---

## Returns

Returns an `ArrayList<String>` containing the deleted document ids.

## Usage

<<< ./snippets/delete-by-query-kotlin.kt

:::
::::