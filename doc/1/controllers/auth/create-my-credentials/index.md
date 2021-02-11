---
code: true
type: page
title: CreateMyCredentials
description: Create the current user's credentials for the specified strategy.
---

# CreateMyCredentials

Creates the current user's credentials for the specified strategy.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> createMyCredentials(final String strategy,
  final Map<String, Object> credentials)
  throws NotConnectedException, InternalException
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>Map<String, Object></pre> | Map representing the credentials |

## Return

A Map representing the new credentials.

## Usage

<<< ./snippets/create-my-credentials-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun createMyCredentials(
      strategy: String,
      credentials: Map<String, Any>): CompletableFuture<Map<String, Any?>>
```

| Argument      | Type               | Description                          |
|---------------|--------------------|--------------------------------------|
| `strategy`    | <pre>String</pre>  | Strategy to use                      |
| `credentials` | <pre>Map<String, Any?></pre> | Map representing the credentials |

## Return

A Map representing the new credentials.

## Usage

<<< ./snippets/create-my-credentials-kotlin.kt

:::
::::