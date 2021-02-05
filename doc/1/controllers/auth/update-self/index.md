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
CompletableFuture<Map<String, Object>> updateSelf(
  final Map<String, Object> content)
  throws NotConnectedException, InternalException
```

| Argument  | Type               | Description                           |
|-----------|--------------------|---------------------------------------|
| `content` | <pre>Map<String, Object></pre> | Hashmap representing the user content |

## Return

Returns a Map with the following properties:

| Property  | Type               | Description                               |
|-----------|--------------------|-------------------------------------------|
| `_id`     | <pre>String</pre>  | User's `kuid`                             |
| `_source` | <pre>Map<String, Object></pre> | Additional (and optional) user properties |

## Usage

<<< ./snippets/update-self-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun updateSelf(
      content: Map<String, Any?>): CompletableFuture<Map<String, Any?>>
```

| Argument  | Type               | Description                           |
|-----------|--------------------|---------------------------------------|
| `content` | <pre>Map<String, Any?></pre> | Hashmap representing the user content |

## Return

Returns a Map with the following properties:

| Property  | Type               | Description                               |
|-----------|--------------------|-------------------------------------------|
| `_id`     | <pre>String</pre>  | User's `kuid`                             |
| `_source` | <pre>Map<String, Any?></pre> | Additional (and optional) user properties |

## Usage

<<< ./snippets/update-self-kotlin.kt

:::
::::