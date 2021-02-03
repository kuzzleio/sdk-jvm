---
code: true
type: page
title: getSpecifications
description: Returns the validation specifications
---

# getSpecifications

Returns the validation specifications associated to the given index and collection.

<br/>

:::: tabs
::: tab Java

## Arguments

```java
  public CompletableFuture<Map<String, Object>> getSpecifications(
      final String index,
      final String collection)
```

<br/>

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `Map<String, Object>` representing the collection specifications.

## Usage

<<< ./snippets/get-specifications-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun getSpecifications(
    index: String,
    collection: String
  ): CompletableFuture<Map<String, Any?>>
```

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `Map<String, Any?>` representing the collection specifications.

## Usage

<<< ./snippets/get-specifications-kotlin.kt

:::
::::