---
code: true
type: page
title: GetMyCredentials
description: Returns the current user's credential information for the specified strategy.
---

# GetMyCredentials

Returns the current user's credential information for the specified strategy. The data returned will depend on the specified strategy. The result can be empty.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> getMyCredentials(String strategy)
  throws NotConnectedException, InternalException
```

| Argument   | Type              | Description     |
|------------|-------------------|-----------------|
| `strategy` | <pre>String</pre> | Strategy to use |

## Return

Returns a ConcurrentHashMap representing the credentials for the provided authentication strategy.

## Usage

<<< ./snippets/get-my-credentials-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun getMyCredentials(
      strategy: String): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

| Argument   | Type              | Description     |
|------------|-------------------|-----------------|
| `strategy` | <pre>String</pre> | Strategy to use |

## Return

Returns a ConcurrentHashMap representing the credentials for the provided authentication strategy.

## Usage

<<< ./snippets/get-my-credentials-kotlin.kt


:::
::::