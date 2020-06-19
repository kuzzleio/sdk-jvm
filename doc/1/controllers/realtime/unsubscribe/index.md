---
code: true
type: page
title: unsubscribe
description: Removes a subscription.
---

# unsubscribe

Removes a subscription.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Void> unsubscribe(final String roomId) 
  throws NotConnectedException, InternalException
```

| Argument  | Type               | Description          |
|-----------|--------------------|----------------------|
| `roomId` | <pre>String</pre>  | Subscription room ID |

## Usage

<<< ./snippets/unsubscribe-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun unsubscribe(roomId: String): CompletableFuture<Void>
```

| Argument  | Type               | Description          |
|-----------|--------------------|----------------------|
| `roomId` | <pre>String</pre>  | Subscription room ID |

## Usage

<<< ./snippets/unsubscribe-kotlin.kt

:::
::::