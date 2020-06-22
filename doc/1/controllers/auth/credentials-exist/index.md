---
code: true
type: page
title: CredentialsExist
description: Checks that the current user has credentials for the specified strategy.
---

# CredentialsExist

Checks that the current user has credentials for the specified strategy.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Boolean> credentialsExist(String strategy)
  throws NotConnectedException, InternalException
```

| Argument   | Type              | Description     |
|------------|-------------------|-----------------|
| `strategy` | <pre>String</pre> | Strategy to use |

## Return

A boolean indicating if credentials exist for the strategy.

## Usage

<<< ./snippets/credentials-exist-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun credentialsExist(strategy: String): CompletableFuture<Boolean>
```

| Argument   | Type              | Description     |
|------------|-------------------|-----------------|
| `strategy` | <pre>String</pre> | Strategy to use |

## Return

A boolean indicating if credentials exist for the strategy.

## Usage

<<< ./snippets/credentials-exist-kotlin.kt

:::
::::