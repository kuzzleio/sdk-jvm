---
code: true
type: page
title: UpdateSelf
description: Updates the current user object in Kuzzle.
---

# UpdateSelf

Updates the current user object in Kuzzle.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<ConcurrentHashMap<String, Object>> updateSelf(
  final ConcurrentHashMap<String, Object> content)
  throws NotConnectedException, InternalException
```

| Argument  | Type               | Description                           |
|-----------|--------------------|---------------------------------------|
| `content` | <pre>ConcurrentHashMap<String, Object></pre> | Hashmap representing the user content |

## Return

Returns a ConcurrentHashMap with the following properties:

| Property  | Type               | Description                               |
|-----------|--------------------|-------------------------------------------|
| `_id`     | <pre>String</pre>  | User's `kuid`                             |
| `_source` | <pre>ConcurrentHashMap<String, Object></pre> | Additional (and optional) user properties |

## Usage

<<< ./snippets/update-self-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun updateSelf(
      content: ConcurrentHashMap<String, Any?>): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

| Argument  | Type               | Description                           |
|-----------|--------------------|---------------------------------------|
| `content` | <pre>ConcurrentHashMap<String, Any?></pre> | Hashmap representing the user content |

## Return

Returns a ConcurrentHashMap with the following properties:

| Property  | Type               | Description                               |
|-----------|--------------------|-------------------------------------------|
| `_id`     | <pre>String</pre>  | User's `kuid`                             |
| `_source` | <pre>ConcurrentHashMap<String, Any?></pre> | Additional (and optional) user properties |

## Usage

<<< ./snippets/update-self-kotlin.kt

:::
::::