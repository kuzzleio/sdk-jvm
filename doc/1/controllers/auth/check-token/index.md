---
code: true
type: page
title: CheckToken
description: Checks an authentication Token's validity.
---

# CheckToken

Checks an authentication token's validity.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> checkToken(String token)
  throws NotConnectedException, InternalException
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `token`  | <pre>String</pre> | Authentication token   |

## Return

A Map which has the following properties:

| Property     | Type              | Description                      |
|--------------|-------------------|----------------------------------|
| `valid`      | <pre>Boolean</pre>   | Token validity                   |
| `state`      | <pre>String</pre> | Explain why the token is invalid |
| `expiresAt` | <pre>int</pre>  | Token expiration timestamp       |

## Usage

<<< ./snippets/check-token-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun checkToken(token: String): CompletableFuture<Map<String, Any?>>
```

| Argument | Type              | Description |
|----------|-------------------|-------------|
| `token`  | <pre>String</pre> | Authentication token   |

## Return

A Map which has the following properties:

| Property     | Type              | Description                      |
|--------------|-------------------|----------------------------------|
| `valid`      | <pre>Boolean</pre>   | Token validity                   |
| `state`      | <pre>String</pre> | Explain why the token is invalid |
| `expiresAt` | <pre>Int</pre>  | Token expiration timestamp       |

## Usage

<<< ./snippets/check-token-kotlin.kt

:::
::::