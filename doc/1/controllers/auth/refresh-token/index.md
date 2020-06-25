---
code: true
type: page
title: RefreshToken
description: Refreshes an authentication token.
---

# RefreshToken

Refreshes an authentication token.

- a valid, non-expired authentication must be provided
- the provided authentication token is revoked
- a new authentication token is generated and returned

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<ConcurrentHashMap<String, Object>> refreshToken(
  String expiresIn) throws NotConnectedException, InternalException

CompletableFuture<ConcurrentHashMap<String, Object>> refreshToken()
  throws NotConnectedException, InternalException
```

**Optional:**

| Argument    | Type              | Description                                                                 |
|-------------|-------------------|-----------------------------------------------------------------------------|
| `expiresIn` | <pre>String</pre> | Set the token expiration duration (default: depends on Kuzzle configuration file) |

## Return

A ConcurrentHashMap with the following properties:

| Property    | Type              | Description                                                                              |
|-------------|-------------------|------------------------------------------------------------------------------------------|
| `_id`       | <pre>String</pre> | User's `kuid`                                                                            |
| `jwt`       | <pre>String</pre> | Encrypted authentication token, that must then be sent in the requests headers or in the query |
| `expiresAt` | <pre>int</pre>  | Token expiration date, in Epoch-millis (UTC)                                             |
| `ttl`       | <pre>int</pre>  | Token time to live, in milliseconds                                                      |
Once `auth:refreshToken` has been called, the returned authentication token is stored by the SDK and used for all the subsequent API calls.

## Usage

<<< ./snippets/refresh-token-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun refreshToken(
      expiresIn: String? = null): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

**Optional:**

| Argument    | Type              | Description                                                                 |
|-------------|-------------------|-----------------------------------------------------------------------------|
| `expiresIn` | <pre>String</pre> | Set the token expiration duration (default: depends on Kuzzle configuration file) |

## Return

A ConcurrentHashMap with the following properties:

| Property    | Type              | Description                                                                              |
|-------------|-------------------|------------------------------------------------------------------------------------------|
| `_id`       | <pre>String</pre> | User's `kuid`                                                                            |
| `jwt`       | <pre>String</pre> | Encrypted authentication token, that must then be sent in the requests headers or in the query |
| `expiresAt` | <pre>Int</pre>  | Token expiration date, in Epoch-millis (UTC)                                             |
| `ttl`       | <pre>Int</pre>  | Token time to live, in milliseconds                                                      |
Once `auth:refreshToken` has been called, the returned authentication token is stored by the SDK and used for all the subsequent API calls.

## Usage

<<< ./snippets/refresh-token-kotlin.kt

:::
::::