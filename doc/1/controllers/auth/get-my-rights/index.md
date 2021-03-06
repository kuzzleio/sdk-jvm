---
code: true
type: page
title: GetMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
---

# GetMyRights

Returns the rights for the currently logged in user within the SDK instance.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<ArrayList<Object>> getMyRights()
  throws NotConnectedException, InternalException
```

## Return

An ArrayList object.

## Usage

<<< ./snippets/get-my-rights-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun getMyRights(): CompletableFuture<ArrayList<Any>>
```

## Return

An ArrayList object.

## Usage

<<< ./snippets/get-my-rights-kotlin.kt

:::
::::