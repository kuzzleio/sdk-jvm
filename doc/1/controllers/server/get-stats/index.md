---
code: true
type: page
title: getStats
description: Returns statistics snapshots within a provided timestamp range.
---

# getStats

Returns statistics snapshots within a provided timestamp range.
By default, snapshots are made every 10 seconds and they are stored for 1 hour.

These statistics include:

- the number of connected users per protocol (not available for all protocols)
- the number of ongoing requests
- the number of completed requests since the last frame
- the number of failed requests since the last frame

:::: tabs
::: tab Java

```java
CompletableFuture<ConcurrentHashMap<String, Object>> getStats(
      Timestamp startTime, Timestamp stopTime)
```

<br/>

| Arguments   | Type                      | Description                                                     |
| ----------- | ------------------------- | --------------------------------------------------------------- |
| `startTime` | <pre>Timestamp</pre> | Beginning of statistics frame set |
| `stopTime`  | <pre>Timestamp</pre> | End of statistics frame set |

## Return

Returns a `ConcurrentHashMap<String, Object>` containing statistics snapshots within the provided range.

## Usage

<<< ./snippets/get-stats-java.java

::: tab Kotlin

```kotlin
fun getStats(startTime: Timestamp, stopTime: Timestamp): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

<br/>

| Arguments   | Type                      | Description                                                     |
| ----------- | ------------------------- | --------------------------------------------------------------- |
| `startTime` | <pre>Timestamp</pre> | Beginning of statistics frame set |
| `stopTime`  | <pre>Timestamp</pre> | End of statistics frame set |

## Return

Returns a `ConcurrentHashMap<String, Any?>` containing statistics snapshots within the provided range.

## Usage

<<< ./snippets/get-stats-kotlin.kt