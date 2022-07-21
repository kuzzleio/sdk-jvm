---
code: true
type: page
title: deleteByQuery
description: Deletes documents matching query.
---

# updateByQuery

Updates documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-dsl.html) syntax.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Int> updateByQuery(
  String index,
  String collection,
  Map<String, Object> searchQuery,
  Map<String, Object> changesQuery,
  Boolean waitForRefresh
) throws NotConnectedException, InternalException

public CompletableFuture<Int> updateByQuery(
  String index,
  String collection,
  Map<String, Object> searchQuery,
  Map<String, Object> changesQuery,
) throws NotConnectedException, InternalException
```

<br/>

| Argument         | Type                           | Description                                                                    |
|------------------|--------------------------------|--------------------------------------------------------------------------------|
| `index`          | <pre>String</pre>              | Index name                                                                     |
| `collection`     | <pre>String</pre>              | Collection name                                                                |
| `searchQuery`    | <pre>Map<String, Object></pre> | JSON representing the query to match                                           |
| `changesQuery`   | <pre>Map<String, Object></pre> | JSON representing the query of the changes to apply                            |
| `waitForRefresh` | <pre>Bool</pre><br>(`false`)   | If set to true, Kuzzle will not respond until the delete documents are indexed |


## Return

An Int containing the number of updated documents.

## Usage

<<< ./snippets/delete-by-query-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun updateByQuery(
  index: String,
  collection: String,
  searchQuery: Map<String, Any?>,
  waitForRefresh: Boolean? = null
): CompletableFuture<Int>
```

<br/>

| Argument     | Type                                 | Description                             |
| ------------ | ------------------------------------ | --------------------------------------- |
| `index`      | <pre>String</pre>        | Index name                              |
| `collection` | <pre>String</pre>        | Collection name                         |
| `searchQuery`      | <pre>Map<String, Any?></pre>        | JSON representing the query to match |
| `changesQuery`   | <pre>Map<String, Any?></pre>        | JSON representing the query of the changes to apply                            |
| `waitForRefresh` | <pre>Boolean</pre><br>(`false`)  | If set to true, Kuzzle will not respond until the delete documents are indexed |


## Return

An Int containing the number of updated documents.

## Usage

<<< ./snippets/update-by-query-kotlin.kt

:::
::::
