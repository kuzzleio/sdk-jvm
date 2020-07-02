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
CompletableFuture<ConcurrentHashMap<String, Object>> info()
```

## Return

Returns a `Map<String, dynamic>` containing server information.

## Usage

<<< ./snippets/info-java.dart

::: tab Kotlin

```kotlin
fun info(): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

## Return

Returns a `ConcurrentHashMap<String, Object>` containing server information.

## Usage

<<< ./snippets/info-kotlin.dart
