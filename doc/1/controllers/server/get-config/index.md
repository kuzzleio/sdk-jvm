---
code: true
type: page
title: getConfig
description: Returns the current Kuzzle configuration.
---

# getConfig

Returns the current Kuzzle configuration.

:::warning
This route should only be accessible to administrators, as it might return sensitive information about the backend.
:::

:::: tabs
::: tab Java

```java
CompletableFuture<Map<String, Object>> getConfig()
```

## Return

Returns a `Map<String, Object>` containing server configuration.

## Usage

<<< ./snippets/get-config-java.java

:::
::: tab Kotlin

```kotlin
fun getConfig(): CompletableFuture<Map<String, Any?>>
```

## Return

Returns a `Map<String, Any?>` containing server configuration.

## Usage

<<< ./snippets/get-config-kotlin.kt

:::
::::