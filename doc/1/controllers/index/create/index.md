---
code: true
type: page
title: create
description: Creates an index.
---

# create

Creates a new index in Kuzzle

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<Void> create(String index) 
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Usage

<<< ./snippets/create-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun create(index: String): CompletableFuture<Void>
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `index`  | <pre>String</pre> | Index name  |

## Usage

<<< ./snippets/create-kotlin.kt

:::
::::