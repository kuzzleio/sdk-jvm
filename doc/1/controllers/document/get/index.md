---
code: true
type: page
title: get
description: Gets a document
---

# get

Gets a document.

---

:::: tabs
::: tab Java

## Arguments
 
```java
public CompletableFuture<ConcurrentHashMap<String, Object>> get(
      String index,
      String collection,
      String id)
throws NotConnectedException, InternalException

```
 
| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id        `       | <pre>String</pre>                            | Document ID                       |

---

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                                                    |
|------------- |----------------------------- |--------------------------------------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Document content                |
| `_id`        | <pre>String</pre>            | ID of the document                                     |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage         |

## Usage

<<< ./snippets/get-java.java

:::
::: tab Kotlin

## Arguments
 
```kotlin
fun get(
      index: String,
      collection: String,
      id: String): CompletableFuture<ConcurrentHashMap<String, Any?>>

```
 
| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id        `       | <pre>String</pre>                            | Document ID                       |

---

## Return

A `ConcurrentHashMap` which has the following properties:

| Property     | Type                         | Description                                                    |
|------------- |----------------------------- |--------------------------------------------------------------- |
| `_source`    | <pre>ConcurrentHashMap</pre> | Document content                |
| `_id`        | <pre>String</pre>            | ID of the document                                     |
| `_version`   | <pre>Integer</pre>           | Version of the document in the persistent data storage         |

## Usage

<<< ./snippets/get-kotlin.kt

:::
::::