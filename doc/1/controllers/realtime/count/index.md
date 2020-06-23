---
code: true
type: page
title: count
description: Returns the number of other connections sharing the same subscription.
---

# count

Returns the number of other connections sharing the same subscription.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Integer> count(String roomId)
  throws NotConnectedException, InternalException
```

| Argument  | Type              | Description          |
|-----------|-------------------|----------------------|
| `roomId` | <pre>String</pre> | Subscription room ID |

## Return

Returns the number of active connections using the same provided subscription room.

## Usage

<<< ./snippets/count-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun count(roomId: String): CompletableFuture<Int>
```

| Argument  | Type              | Description          |
|-----------|-------------------|----------------------|
| `roomId` | <pre>String</pre> | Subscription room ID |

## Return

Returns the number of active connections using the same provided subscription room.

## Usage

<<< ./snippets/count-kotlin.kt

:::
::::