---
code: true
type: page
title: adminExists
description: Checks that an administrator account exists.
---

# adminExists

Checks that an administrator account exists.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<Boolean> adminExists()
```

## Return

A boolean indicating whether an admin user exists or not.

## Usage

<<< ./snippets/adminExists-java.java

:::: tabs
::: tab Kotlin

## Arguments

```kotlin
fun adminExists(): CompletableFuture<Boolean>
```

## Return

A boolean indicating whether an admin user exists or not.

## Usage

<<< ./snippets/adminExists-kotlin.kotlin
