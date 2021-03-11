---
code: true
type: page
title: info
description: Returns information about Kuzzle server.
---

# info

Returns information about Kuzzle: available API (base + extended), plugins, external services (Redis, Elasticsearch, ...), servers, etc.

:::: tabs
::: tab Java

```java
CompletableFuture<Map<String, Object>> info()
```

## Return

Returns a `Map<String, dynamic>` containing server information.

## Usage

<<< ./snippets/info-java.java

:::
::: tab Kotlin

```kotlin
fun info(): CompletableFuture<Map<String, Any?>>
```

## Return

Returns a `Map<String, Object>` containing server information.

## Usage

<<< ./snippets/info-kotlin.kt

:::
::::
