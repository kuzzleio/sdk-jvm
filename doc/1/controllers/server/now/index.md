---
code: true
type: page
title: now
description: Returns the current server timestamp.
---

# now

Returns the current server timestamp.

:::: tabs
::: tab Java

```java
CompletableFuture<Date> now()
```

## Return

Returns a `Date` representing the current server timestamp.

## Usage

<<< ./snippets/now-java.java

::: tab Kotlin

```kotlin
fun now(): CompletableFuture<Date>
```

## Return

Returns a `Date` representing the current server timestamp.

## Usage

<<< ./snippets/now-kotlin.kt