---
code: true
type: page
title: now
description: Returns the current server timestamp, in Epoch-millis format.
---

# now

Returns the current server timestamp, in Epoch-millis format.

:::: tabs
::: tab Java

```java
CompletableFuture<Timestamp> now()
```

## Return

Returns a `Timetamps` representing the current server timestamp.

## Usage

<<< ./snippets/now-java.java

::: tab Kotlin

```kotlin
fun now(): CompletableFuture<Timestamp>
```

## Return

Returns a `Timestamp` representing the current server timestamp.

## Usage

<<< ./snippets/now-kotlin.kt