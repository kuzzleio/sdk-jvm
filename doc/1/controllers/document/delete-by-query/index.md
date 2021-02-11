---
code: true
type: page
title: deleteByQuery
description: Delete documents matching query
---

# deleteByQuery

Deletes documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<SinceBadge version="1.1.0"/>

This method also supports the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) to match documents by passing the `lang` argument with the value `koncorde`.  
Koncorde filters will be translated into an Elasticsearch query.  

::: warning
Koncorde `bool` operator and `regexp` clauses are not supported for search queries.
:::

An empty or null query will match all documents in the collection.

:::: tabs
::: tab Java

## Arguments

```java
  public CompletableFuture<ArrayList<String>> deleteByQuery(
      String index,
      String collection,
      Map<String, Object> searchQuery) throws NotConnectedException, InternalException

  public CompletableFuture<ArrayList<String>> deleteByQuery(
      String index,
      String collection,
      Map<String, Object> searchQuery,
      Lang lang) throws NotConnectedException, InternalException

  public CompletableFuture<ArrayList<String>> deleteByQuery(
      String index,
      String collection,
      Map<String, Object> searchQuery,
      Boolean waitForRefresh) throws NotConnectedException, InternalException

  public CompletableFuture<ArrayList<String>> deleteByQuery(
      String index,
      String collection,
      Map<String, Object> searchQuery,
      Boolean waitForRefresh,
      Lang lang) throws NotConnectedException, InternalException
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>Map<String, Object></pre> | Query to match  |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |
| `lang`     | <pre>[Lang](/sdk/jvm/1/core-classes/lang)</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="1.1.0"/> |

---

## Returns

Returns an `ArrayList<String>` containing the deleted document ids.

## Usage

With the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<<< ./snippets/delete-by-query-es-java.java

With the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.


<<< ./snippets/delete-by-query-koncorde-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun deleteByQuery(
      index: String,
      collection: String,
      searchQuery: Map<String, Any?>,
      waitForRefresh: Boolean? = null,
      lang: Lang = Lang.ELASTICSEARCH): CompletableFuture<ArrayList<String>>
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>Map<String, Any?></pre> | Query to match  |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |
| `lang`     | <pre>[Lang](/sdk/jvm/1/core-classes/lang)</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="1.1.0"/> |

---

## Returns

Returns an `ArrayList<String>` containing the deleted document ids.

## Usage

With the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<<< ./snippets/delete-by-query-es-kotlin.kt

With the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.


<<< ./snippets/delete-by-query-koncorde-kotlin.kt

:::
::::
