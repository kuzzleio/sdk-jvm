---
code: true
type: page
title: searchSpecifications
description: Search for specifications
---

# searchSpecifications


Searches collection specifications.

There is a limit to how many items can be returned by a single search query.
That limit is by default set at 10000, and you can't get over it even with the from and size pagination options.

:::info
When processing a large number of items (i.e. more than 1000), it is advised to paginate the results using [SearchResult.next](/sdk/java/3/core-classes/search-result/next) rather than increasing the size parameter.
:::

<br/>

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<SearchResult> searchSpecifications(
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll,
      Integer from,
      Integer size) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> searchSpecifications(
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll,
      Integer from) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> searchSpecifications(
      ConcurrentHashMap<String, Object> searchQuery,
      String scroll) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> searchSpecifications(
      ConcurrentHashMap<String, Object> searchQuery)
throws NotConnectedException, InternalException
```

<br/>

| Arguments | Type              | Description                           |
| --------- | ----------------- | ------------------------------------- |
| `searchQuery`    | <pre>ConcurrentHashMap<String, Object></pre> | An object containing the search query |
| `from`     | <pre>Integer</pre><br/>(`0`)    | Offset of the first document to fetch                                                                                                                                                                             |
| `size`     | <pre>Integer</pre><br/>(`10`)   | Maximum number of documents to retrieve per page                                                                                                                                                                  |
| `scroll`   | <pre>String</pre><br/>(`""`)    | When set, gets a forward-only cursor having its ttl set to the given value (e.g. `1s`; see [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/common-options.html#time-units)) |

### searchQuery body properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.

## Usage

<<< ./snippets/search-specifications-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun searchSpecifications(
    searchQuery: ConcurrentHashMap<String, Any?>,
    scroll: String? = null,
    from: Int = 0,
    size: Int? = null
  ): CompletableFuture<SearchResult>
```

<br/>

| Arguments | Type              | Description                           |
| --------- | ----------------- | ------------------------------------- |
| `searchQuery`    | <pre>ConcurrentHashMap<String, Any?></pre> | An object containing the search query |
| `from`     | <pre>Int</pre><br/>(`0`)    | Offset of the first document to fetch                                                                                                                                                                             |
| `size`     | <pre>Int</pre><br/>(`10`)   | Maximum number of documents to retrieve per page                                                                                                                                                                  |
| `scroll`   | <pre>String</pre><br/>(`""`)    | When set, gets a forward-only cursor having its ttl set to the given value (ie `1s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/common-options.html#time-units)) |

### searchQuery body properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.

## Usage

<<< ./snippets/search-specifications-kotlin.kt

:::
::::
