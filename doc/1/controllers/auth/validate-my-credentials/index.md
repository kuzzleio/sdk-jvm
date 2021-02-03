---
code: true
type: page
title: ValidateMyCredentials
description: Validates the current user's credentials for the specified strategy.
---

# ValidateMyCredentials

Validates the current user's credentials for the specified strategy. This method returns `true` if the provided credentials are valid; otherwise an error is triggered. This route does not actually create or modify the user credentials. The credentials to send will depend on the authentication plugin and authentication strategy.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<Boolean> validateMyCredentials(final String strategy,
  final Map<String, Object> credentials)
  throws NotConnectedException, InternalException
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>Map<String, Object></pre> | A Hashmap representing the credentials |

## Return

A Boolean indicating if the credentials are valid.

## Usage

<<< ./snippets/validate-my-credentials-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun validateMyCredentials(strategy: String,
  credentials: Map<String, Any?>): CompletableFuture<Boolean>
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>Map<String, Any?></pre> | A Hashmap representing the credentials |

## Return

A Boolean indicating if the credentials are valid.

## Usage

<<< ./snippets/validate-my-credentials-kotlin.kt

:::
::::