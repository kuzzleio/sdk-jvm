---
code: true
type: page
title: deleteSpecifications
description: Delete validation specifications for a collection
---

# deleteSpecifications

Deletes validation specifications for a collection.

<br/>

:::: tabs
::: tab Java

## Arguments

```java
  public CompletableFuture<Void> deleteSpecifications(
      final String index,
      final String collection)
```

<br/>

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `CompletableFuture<Void>`.

## Usage

<<< ./snippets/delete-specifications-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun deleteSpecifications(
      index: String,
      collection: String
    ): CompletableFuture<Void>
```

| Arguments    | Type              | Description     |
| ------------ | ----------------- | --------------- |
| `index`      | <pre>String</pre> | Index name      |
| `collection` | <pre>String</pre> | Collection name |

## Returns

Returns a `CompletableFuture<Void>`.

## Usage

<<< ./snippets/delete-specifications-kotlin.kt

:::
::::