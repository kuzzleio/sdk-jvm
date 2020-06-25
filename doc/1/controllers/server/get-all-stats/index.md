---
code: true
type: page
title: getAllStats
description: Gets all stored internal statistic snapshots.
---

# getAllStats

Gets all stored internal statistic snapshots.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

- the number of connected users per protocol (not available for all protocols)
- the number of ongoing requests
- the number of completed requests since the last frame
- the number of failed requests since the last frame

:::: tabs
::: tab Java

```java
CompletableFuture<ConcurrentHashMap<String, Object>> getAllStats()
```

## Return

Returns a `ConcurrentHashMap<String, Object>` containing all stored internal statistic snapshots.

## Usage

<<< ./snippets/get-all-stats-java.java

::: tab Kotlin

```kotlin
fun getAllStats(): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

## Return

Returns a `ConcurrentHashMap<String, Any?>` containing all stored internal statistic snapshots.

## Usage

<<< ./snippets/get-all-stats-kotlin.kt