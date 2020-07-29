---
code: true
type: page
title: count
description: Counts documents in a collection.
---

# count

Counts documents in a collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Integer> count(
      String index,
      String collection)
throws NotConnectedException, InternalException

public CompletableFuture<Integer> count(
      String index,
      String collection,
      ConcurrentHaspMap<String, Object> searchQuery)
throws NotConnectedException, InternalException
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Object></pre><br>(`{}`) | Query to match  |

---

## Return

Returns an Integer.

## Usage

<<< ./snippets/count-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun count(
      index: String,
      collection: String,
      searchQuery: ConcurrentHashMap<String, Any?> = ConcurrentHashMap<String, Any?>()): CompletableFuture<Int>
```

| Argument           | Type                                         | Description     |
| ------------------ | -------------------------------------------- | --------------- |
| `index`            | <pre>String</pre>                            | Index name      |
| `collection`       | <pre>String</pre>                            | Collection name |
| `searchQuery`      | <pre>ConcurrentHashMap<String, Any?></pre><br>(`{}`) | Query to match  |

---

## Return

Returns an Integer.

## Usage

<<< ./snippets/count-kotlin.kt

:::
::::
