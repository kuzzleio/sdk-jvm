---
code: true
type: page
title: GetCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`.
---

# GetCurrentUserAsync

Returns information about the user currently logged with the SDK instance.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<ConcurrentHashMap<String, Object>> getCurrentUser()
  throws NotConnectedException, InternalException
```

## Return

A ConcurrentHashMap representing the User.

| Property     | Type               | Description                                       |
|--------------|--------------------|---------------------------------------------------|
| `_id`        | <pre>String</pre>  | Representing the current user `kuid`              |
| `strategies` | <pre>Array</pre>  | Available authentication strategies for that user |
| `_source`    | <pre>ConcurrentHashMap</pre> | User information                                  |

## Usage

<<< ./snippets/get-current-user-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun getCurrentUser(): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

## Return

A ConcurrentHashMap representing the User.

| Property     | Type               | Description                                       |
|--------------|--------------------|---------------------------------------------------|
| `_id`        | <pre>String</pre>  | Representing the current user `kuid`              |
| `strategies` | <pre>Array</pre>  | Available authentication strategies for that user |
| `_source`    | <pre>ConcurrentHashMap</pre> | User information                                  |

## Usage

<<< ./snippets/get-current-user-kotlin.kt

:::
::::
