---
code: true
type: page
title: Query
description: Base method to send API query to Kuzzle
---

# query

Base method used to send queries to Kuzzle, following the [API Documentation](/core/2/api).

:::warning
This is a low-level method, exposed to allow advanced SDK users to bypass high-level methods.
:::

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Response> query(
  Map<String, Object> query)
      throws InternalException, NotConnectedException
```

<SinceBadge version="1.2.3"/>

```java
public CompletableFuture<Response> query(
  String query)
      throws InternalException, NotConnectedException

public CompletableFuture<Response> query(
  RawJson query)
      throws InternalException, NotConnectedException

public CompletableFuture<Response> query(
  RequestPayload query)
      throws InternalException, NotConnectedException

public CompletableFuture<Response> query(
  Object query)
      throws InternalException, NotConnectedException
```

<br/>

| Argument  | Type              | Description            |
| --------- | ----------------- | ---------------------- |
| `query` | <pre>Map<String, Object> | API request    |

<SinceBadge version="1.2.3"/>

| Argument  | Type              | Description            |
| --------- | ----------------- | ---------------------- |
| `query` | <pre>Map<String, Object><br>or String<br>or RawJson<br>or RequestPayload<br>or Object</pre> | API request    |

::: info
Calling query with a `String` or `RawJson` makes no differences, and will be interpreted as raw json strings.
:::

::: warn
Calling query directly with a `String` or `RawJson` causes the SDK to deserialize the query, add some properties, then reserialize the query.

This can decrease performances when giving big JSON payloads.

If you have big JSON body payloads to send, consider using a Map and only use a RawJson for the body property.
This will avoid the deserialization + reserialization slowdown
:::

### query

All properties necessary for the Kuzzle API can be added in the query object.
The following properties are the most common.

| Property     | Type              | Description                              |
| ------------ | ----------------- | ---------------------------------------- |
| `controller` | <pre>String</pre> | Controller name (mandatory)              |
| `action`     | <pre>String</pre> | Action name (mandatory)                  |
| `body`       | <pre>Map<String, Object><br>or RawJson<br>or Object</pre> | Query body for this action               |
| `index`      | <pre>String</pre> | Index name for this action               |
| `collection` | <pre>String</pre> | Collection name for this action          |
| `_id`        | <pre>String</pre> | id for this action                       |
| `volatile`   | <pre>Map<String, Object><br>or RawJson<br>or Object</pre> | Additional information to send to Kuzzle |

## Returns

Returns a [Response](/sdk/jvm/1/core-classes/response) object which represents a raw Kuzzle API response. See the [API Documentation](/core/2/api).

## Usage

<<< ./snippets/query-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun query(query: Map<String?, Any?>): CompletableFuture<Response>
```

<SinceBadge version="1.2.3"/>

```kotlin
fun query(query: RawJson): CompletableFuture<Response>
fun query(query: String): CompletableFuture<Response>
fun query(query: RequestPayload): CompletableFuture<Response>
fun query(query: Any): CompletableFuture<Response>
```

<br/>

| Argument  | Type              | Description            |
| --------- | ----------------- | ---------------------- |
| `query` | <pre>Map<String?, Any?> | API request    |

<SinceBadge version="1.2.3"/>

| Argument  | Type              | Description            |
| --------- | ----------------- | ---------------------- |
| `query` | <pre>Map<String?, Any?><br>or RawJson<br>or String<br>or RequestPayload<br>or Any</pre> | API request    |

::: info
Calling query with a `String` or `RawJson` makes no differences, and will be interpreted as raw json strings.
:::

::: warn
Calling query directly with a `String` or `RawJson` causes the SDK to deserialize the query, add some properties, then reserialize the query.

This can decrease performances when giving big JSON payloads.

If you have big JSON body payloads to send, consider using a Map and only use a RawJson for the body property.
This will avoid the deserialization + reserialization slowdown
:::

### query

All properties necessary for the Kuzzle API can be added in the query object.
The following properties are the most common.

| Property     | Type              | Description                              |
| ------------ | ----------------- | ---------------------------------------- |
| `controller` | <pre>String</pre> | Controller name (mandatory)              |
| `action`     | <pre>String</pre> | Action name (mandatory)                  |
| `body`       | <pre>Map<String, Object><br>or RawJson<br>or Any</pre> | Query body for this action               |
| `index`      | <pre>String</pre> | Index name for this action               |
| `collection` | <pre>String</pre> | Collection name for this action          |
| `_id`        | <pre>String</pre> | id for this action                       |
| `volatile`   | <pre>Map<String, Object><br>or RawJson<br>or Any</pre> | Additional information to send to Kuzzle |

## Returns

Returns a [Response](/sdk/jvm/1/core-classes/response) object which represents a raw Kuzzle API response. See the [API Documentation](/core/2/api).

## Usage

<<< ./snippets/query-kotlin.kt

:::
::::