---
code: true
type: page
title: UpdateMyCredentials
description: Updates the current user's credentials for the specified strategy.
---

# UpdateMyCredentials

Updates the current user's credentials for the specified strategy. The credentials to send will depend on the authentication plugin and the authentication strategy.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<Map<String, Object>> updateMyCredentials(
  String strategy,
  Map<String, Object> credentials)
  throws NotConnectedException, InternalException
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>Map<String, Object></pre> | JObject representing the credentials |

## Return

A Map representing the updated credentials with the following properties:

| Property   | Type              | Description       |
|------------|-------------------|-------------------|
| `username` | <pre>String</pre> | The Username      |
| `kuid`     | <pre>String</pre> | The user's `kuid` |

## Usage

<<< ./snippets/update-my-credentials-java.java

:::
::: tab Kotlin
## Arguments

```kotlin
fun updateMyCredentials(
      strategy: String,
      credentials: Map<String, Any?>): CompletableFuture<Map<String, Any?>>
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>Map<String, Any?></pre> | JObject representing the credentials |

## Return

A Map representing the updated credentials with the following properties:

| Property   | Type              | Description       |
|------------|-------------------|-------------------|
| `username` | <pre>String</pre> | The Username      |
| `kuid`     | <pre>String</pre> | The user's `kuid` |

## Usage

<<< ./snippets/update-my-credentials-kotlin.kt

:::
::::