---
code: true
type: page
title: search
description: Searches a document
---

# search

Searches document.

::: warning
There is a limit to how many documents can be returned by a single search query.
That limit is by default set at 10000 documents, and you can't get over it even with the from and size pagination options.
:::

::: info
When processing a large number of documents (i.e. more than 1000), it is advised to paginate the results using [SearchResult.next](/sdk/jvm/1/core-classes/search-result/next) rather than increasing the size parameter.
:::

::: warning
When using a cursor with the `scroll` option, Elasticsearch has to duplicate the transaction log to keep the same result during the entire scroll session.  
It can lead to memory leaks if a scroll duration too great is provided, or if too many scroll sessions are open simultaneously.  
:::

::: info
<SinceBadge version="Kuzzle 2.2.0"/>
You can restrict the scroll session maximum duration under the `services.storage.maxScrollDuration` configuration key.
:::

<SinceBadge version="change-me"/>

This method also supports the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) to match documents by passing the `lang` argument with the value `koncorde`.  
Koncorde filters will be translated into an Elasticsearch query.  

::: warning
Koncorde `bool` operator and `regexp` clause are not supported for search queries.
:::

---

:::: tabs
::: tab Java

## Arguments
 
```java
public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String lang) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll,
      String lang) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll,
      Integer size,
      String lang) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> search(
      String index,
      String collection,
      ConcurrentHashMap<String, Object> searchQuery
      Integer size,
      Integer from,
      String lang)
throws NotConnectedException, InternalException
```
 
| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `searchQuery`      | <pre>ConcurrentHashMap</pre>                 | Search query                      |
| `from`     | <pre>Integer</pre><br/>(`0`)    | Offset of the first document to fetch                                                                                                                                                                             |
| `size`     | <pre>Integer</pre><br/>(`10`)   | Maximum number of documents to retrieve per page                                                                                                                                                                  |
| `scroll`   | <pre>String</pre><br/>(`""`)    | When set, gets a forward-only cursor having its ttl set to the given value (ie `1s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/common-options.html#time-units)) |
| `lang`     | <pre>String</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |
---

### searchQuery body properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) or the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.

## Usage

<<< ./snippets/search-java.java

:::
::: tab Kotlin


## Arguments
 
```kotlin
  fun search(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String?, Any?>,
      scroll: String? = null,
      size: Int? = null,
      from: Int = 0,
      land: String? = null): CompletableFuture<SearchResult>
```
 
| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `searchQuery`      | <pre>ConcurrentHashMap</pre>                 | Search query                      |
| `from`     | <pre>Int</pre><br/>(`0`)    | Offset of the first document to fetch                                                                                                                                                                             |
| `size`     | <pre>Int</pre><br/>(`10`)   | Maximum number of documents to retrieve per page                                                                                                                                                                  |
| `scroll`   | <pre>String</pre><br/>(`""`)    | When set, gets a forward-only cursor having its ttl set to the given value (ie `1s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/common-options.html#time-units)) |
| `lang`     | <pre>String</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |
---

### searchQuery body properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) or the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.

## Usage

<<< ./snippets/search-kotlin.kt

:::
::::
