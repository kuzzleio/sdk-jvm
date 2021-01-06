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
```


| Property | Type | Description |
| --- | --- | --- |
| `query` | <pre>ConcurrentHashMap<String, Object></pre> | Search query |
| `from`     | <pre>Integer</pre><br/>(`0`)     | (optional) Offset of the first document to fetch   |
| `size`     | <pre>Integer</pre>    | (optional) Maximum number of documents to retrieve per page     |

### query

The search query to apply to API keys content, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) syntax.

If left empty, the result will return all available API keys of the currently loggued user.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.


## Usage

<<< ./snippets/search-api-keys-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun searchApiKeys(
    query: ConcurrentHashMap<String, Any?>,
    from: Int = 0,
    size: Int? = null): CompletableFuture<SearchResult>
```


| Property | Type | Description |
| --- | --- | --- |
| `query` | <pre>ConcurrentHashMap<String, Any?></pre> | Search query |
| `from`     | <pre>Int</pre><br/>(`0`)     | (optionnal) Offset of the first document to fetch   |
| `size`     | <pre>Int</pre>    | (optionnal) Maximum number of documents to retrieve per page     |

### query

The search query to apply to API keys content, using [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/query-dsl.html) syntax.

If left empty, the result will return all available API keys of the currently loggued user.

## Return

Returns a [SearchResult](/sdk/jvm/1/core-classes/search-result) object.


## Usage

<<< ./snippets/search-api-keys-kotlin.kt

:::
::::
