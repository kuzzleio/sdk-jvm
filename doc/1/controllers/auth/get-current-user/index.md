---
code: true
type: page
title: GetCurrentUser
description: Returns the profile object for the user linked to the `JSON Web Token`.
---

# GetCurrentUserAsync

Returns informations about the user currently loggued with the SDK instance.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> getCurrentUser()
  throws NotConnectedException, InternalException
```

## Return

A Map representing the User.

| Property     | Type               | Description                                       |
|--------------|--------------------|---------------------------------------------------|
| `_id`        | <pre>String</pre>  | Representing the current user `kuid`              |
| `strategies` | <pre>Array</pre>  | Available authentication strategies for that user |
| `_source`    | <pre>Map</pre> | User information                                  |

## Usage

<<< ./snippets/get-current-user-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun getCurrentUser(): CompletableFuture<Map<String, Any?>>
```

## Return

A Map representing the User.

| Property     | Type               | Description                                       |
|--------------|--------------------|---------------------------------------------------|
| `_id`        | <pre>String</pre>  | Representing the current user `kuid`              |
| `strategies` | <pre>Array</pre>  | Available authentication strategies for that user |
| `_source`    | <pre>Map</pre> | User information                                  |

## Usage

<<< ./snippets/get-current-user-kotlin.kt

:::
::::