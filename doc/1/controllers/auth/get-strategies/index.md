---
code: true
type: page
title: GetStrategies
description: Get all authentication strategies registered in Kuzzle.
---

# GetStrategies

Gets all authentication strategies registered in Kuzzle.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<ArrayList<String>> getStrategies()
  throws NotConnectedException, InternalException
```

## Return

An ArrayList representing the available authentication strategies.

## Usage

<<< ./snippets/get-strategies-java.java

:::
::: Kotlin

## Arguments

```kotlin
fun getStrategies(): CompletableFuture<ArrayList<String>>
```

## Return

An ArrayList representing the available authentication strategies.

## Usage

<<< ./snippets/get-strategies-kotlin.kt

:::
::::