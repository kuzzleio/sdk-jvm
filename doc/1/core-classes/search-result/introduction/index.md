---
code: true
type: page
title: constructor
description: SearchResult:constructor
order: 1
---

# SearchResults

This class represents a paginated search result.  

It can be returned by the following methods:
 - [document:search](/sdk/jvm/1/controllers/document/search)
 - [collection:searchSpecifications](/sdk/jvm/1/controllers/collection/search-specifications)

:::: tabs
::: tab Java

## Namespace

You must include the following package: 

```java
import io.kuzzle.sdk.coreClasses.SearchResult;
```

## Properties

| Property       | Type                                                    | Description        |
| -------------- | ------------------------------------------------------- | ------------------ |
| `aggregations` | <pre>Map<String, Object></pre>            | Search aggregations (can be undefined) |
| `hits`         | <pre>ArrayList<Map<String, Object>></pre> | Page results       |
| `total`        | <pre>Integer</pre>                                      |  Total number of items that _can_ be retrieved |
| `fetched`      | <pre>Integer</pre>                                      | Number of retrieved items so far   |

### hits

Each element of the `hits` ArrayList is a `Map<String, Object>` containing the following properties:

| Property  | Type               | Description            |
| --------- | ------------------ | ---------------------- |
| `_id`     | <pre>String</pre>  | Document ID            |
| `_score`  | <pre>Integer</pre> | [Relevance score](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html) |
| `_source` | <pre>Map<String, Object></pre> | Document content |


:::
::: tab Kotlin
## Namespace

You must include the following package: 

```kotlin
import io.kuzzle.sdk.coreClasses.SearchResult;
```

## Properties

| Property       | Type                                                    | Description        |
| -------------- | ------------------------------------------------------- | ------------------ |
| `aggregations` | <pre>Map<String, Any></pre>            | Search aggregations (can be undefined) |
| `hits`         | <pre>ArrayList<Map<String, Any>></pre> | Page results       |
| `total`        | <pre>Int</pre>                                      |  Total number of items that _can_ be retrieved |
| `fetched`      | <pre>Int</pre>                                      | Number of retrieved items so far   |

### hits

Each element of the `hits` ArrayList is a `Map<String, Object>` containing the following properties:

| Property  | Type               | Description            |
| --------- | ------------------ | ---------------------- |
| `_id`     | <pre>String</pre>  | Document ID            |
| `_score`  | <pre>Int</pre> | [Relevance score](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html) |
| `_source` | <pre>Map<String, Any?></pre> | Document content |

:::
::::
