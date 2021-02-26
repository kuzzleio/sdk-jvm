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

```java
CompletableFuture<Boolean> adminExists()
```

## Return

A boolean indicating whether an admin user exists or not.

## Usage

<<< ./snippets/admin-exists-java.java

:::
::: tab Kotlin

```kotlin
fun adminExists(): CompletableFuture<Boolean>
```

## Return

A boolean indicating whether an admin user exists or not.

## Usage

<<< ./snippets/admin-exists-kotlin.kt

:::
::::
