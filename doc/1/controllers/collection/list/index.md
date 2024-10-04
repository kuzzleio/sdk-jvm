---
code: true
type: page
title: list
description: Returns the collection list of an index
---

# list

Returns the list of collections associated to a provided index.
The returned list is sorted in alphanumerical order.

<br/>

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> list(
      final String index) throws NotConnectedException, InternalException
```

| Arguments | Type     | Description |
| --------- | -------- | ----------- |
| `index`   | `String` | Index name  |

## Returns

Returns a `Map<String, Object>` containing the following properties:

| Property      | Type                | Description                                                        |
| ------------- | ------------------- | ------------------------------------------------------------------ |
| `type`        | `String`            | Types of returned collections <br/>(`all`, `realtime` or `stored`) |
| `collections` | `ArrayList<Object>` | List of collections                                                |
| `from`        | `Integer`           | Offset of the first result                                         |
| `size`        | `Integer`           | Maximum number of returned results                                 |

Each object in the `collections` array contains the following properties:

| Property | Type     | Description                              |
| -------- | -------- | ---------------------------------------- |
| `name`   | `String` | Collection name                          |
| `type`   | `String` | Collection type (`realtime` or `stored`) |

## Usage

\<\<\< ./snippets/list-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun list(index: String): CompletableFuture<Map<String, Any?>>
```

| Arguments | Type     | Description |
| --------- | -------- | ----------- |
| `index`   | `String` | Index name  |

## Returns

Returns a `Map<String, Any?>` containing the following properties:

| Property      | Type                | Description                                                        |
| ------------- | ------------------- | ------------------------------------------------------------------ |
| `type`        | `String`            | Types of returned collections <br/>(`all`, `realtime` or `stored`) |
| `collections` | `ArrayList<Object>` | List of collections                                                |
| `from`        | `Int`               | Offset of the first result                                         |
| `size`        | `Int`               | Maximum number of returned results                                 |

Each object in the `collections` array contains the following properties:

| Property | Type     | Description                              |
| -------- | -------- | ---------------------------------------- |
| `name`   | `String` | Collection name                          |
| `type`   | `String` | Collection type (`realtime` or `stored`) |

## Usage

\<\<\< ./snippets/list-kotlin.kt

:::
::::
