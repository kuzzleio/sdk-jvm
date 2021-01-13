---
code: true
type: page
title: searchApiKeys
description: Searches API keys for the currently logged user.
---

# searchApiKeys

<SinceBadge version="auto-version" />

<SinceBadge version="Kuzzle 2.1.0" />

Searches API keys for the currently logged user.

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
public CompletableFuture<SearchResult> searchApiKeys(
  ConcurrentHashMap<String, Object> query) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> searchApiKeys(
  ConcurrentHashMap<String, Object> query,
  Integer from) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> searchApiKeys(
  ConcurrentHashMap<String, Object> query
  Integer from
  Integer size) throws NotConnectedException, InternalException

public CompletableFuture<SearchResult> searchApiKeys(
  ConcurrentHashMap<String, Object> query
  Integer from
  Integer size,
  Lang lang) throws NotConnectedException, InternalException
```


| Property | Type | Description |
| --- | --- | --- |
| `query` | <pre>ConcurrentHashMap<String, Object></pre> | Search query |
| `from`     | <pre>Integer</pre><br/>(`0`)     | (optional) Offset of the first document to fetch   |
| `size`     | <pre>Integer</pre>    | (optional) Maximum number of documents to retrieve per page     |
| `lang`     | <pre>[Lang](/sdk/jvm/1/core-classes/lang)</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |

### query

The search query to apply to API keys content, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) syntax.

If left empty, the result will return all available API keys for the currently logged user.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.


## Usage

With the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<<< ./snippets/search-api-keys-es-java.java

With the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.


<<< ./snippets/search-api-keys-koncorde-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun searchApiKeys(
    query: ConcurrentHashMap<String, Any?>,
    from: Int = 0,
    size: Int? = null,
    lang: Lang = Lang.ELASTICSEARCH): CompletableFuture<SearchResult>
```


| Property | Type | Description |
| --- | --- | --- |
| `query` | <pre>ConcurrentHashMap<String, Any?></pre> | Search query |
| `from`     | <pre>Int</pre><br/>(`0`)     | (optional) Offset of the first document to fetch   |
| `size`     | <pre>Int</pre>    | (optional) Maximum number of documents to retrieve per page     |
| `lang`     | <pre>[Lang](/sdk/jvm/1/core-classes/lang)</pre>               | Specify the query language to use. By default, it's `elasticsearch` but `koncorde` can also be used. <SinceBadge version="change-me"/> |

### query

The search query to apply to API keys content, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) syntax.

If left empty, the result will return all available API keys of the currently logged user.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.


## Usage

With the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

<<< ./snippets/search-api-keys-es-kotlin.kt

With the [Koncorde Filters DSL](/core/2/api/koncorde-filters-syntax) syntax.


<<< ./snippets/search-api-keys-koncorde-kotlin.kt

:::
::::
