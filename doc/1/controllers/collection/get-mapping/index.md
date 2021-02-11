---
code: true
type: page
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the collection mapping.

<br/>

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> getMapping(
      final String index,
      final String collection) throws NotConnectedException, InternalException
```

<br/>

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `Map<String, Object>` representing the collection mappings.

## Usage

<<< ./snippets/get-mapping-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun getMapping(
    index: String,
    collection: String
  ): CompletableFuture<Map<String, Any?>>
```

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |


## Returns

Returns a `Map<String, Any?>` representing the collection mappings.

## Usage

<<< ./snippets/get-mapping-kotlin.kt

:::
::::