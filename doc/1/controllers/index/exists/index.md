---
code: true
type: page
title: exists
description: Check for index existence.
---

# exists

Checks if the given index exists in Kuzzle.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<Boolean> exists(String index) 
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Return

Returns a `Boolean` that indicates whether the index exists or not.

## Usage

<<< ./snippets/exists-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun exists(index: String): CompletableFuture<Boolean>
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Return

Returns a `Boolean` that indicates whether the index exists or not.

## Usage

<<< ./snippets/exists-kotlin.kt

:::
::::