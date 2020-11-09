---
code: true
type: page
title: list
description: Lists the indexes.
---

# list

Gets the complete list of indexes handled by Kuzzle.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<ArrayList<String>> list() 
  throws NotConnectedException, InternalException
```

## Return

Returns an `ArrayList<String>` containing the list of index names handled by Kuzzle.

## Usage

<<< ./snippets/list-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun list(): CompletableFuture<ArrayList<String>>
```

## Return

Returns an `ArrayList<String>` containing the list of index names handled by Kuzzle.

## Usage

<<< ./snippets/list-kotlin.kt

:::
::::