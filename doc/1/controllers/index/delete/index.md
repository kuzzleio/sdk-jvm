---
code: true
type: page
title: delete
description: Deletes an index.
---

# delete

Deletes an entire index from Kuzzle.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<Void> delete(String index)
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Usage

<<< ./snippets/delete-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun delete(index: String): CompletableFuture<Void>
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Usage

<<< ./snippets/delete-kotlin.kotlin

:::
::::