---
code: true
type: page
title: mDelete
description: Deletes multiple documents
---

# mDelete

Deletes multiple documents.

---

:::: tabs
::: tab Java

## Arguments 

```java
public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mDelete(
      String index,
      String collection,
      ArrayList<String> ids)
throws NotConnectedException, InternalException

public CompletableFuture<ConcurrentHashMap<String, ArrayList<Object>>> mDelete(
      String index,
      String collection,
      ArrayList<String> ids,
      Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index name                        |
| `collection`       | <pre>String</pre>                                       | Collection name                   |
| `ids`              | `ArrayList<String>`                            | Document IDs                      |
| `waitForRefresh`   | <pre>Boolean</pre>                                      | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |

---

## Return

A `ConcurrentHashMap<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
The `successes` array contains the successfully deleted document IDs.

Each deletion error is an object of the errors array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_id`        | <pre>String</pre>                            | Document ID                      |
| `reason`     | <pre>String</pre>                            | Human readable reason            |

## Usage

<<< ./snippets/m-delete-java.java

:::
::: tab Kotlin

## Arguments 

```kotlin
fun mDelete(
      index: String,
      collection: String,
      ids: ArrayList<String>,
      waitForRefresh: Boolean? = null): CompletableFuture<ConcurrentHashMap<String, ArrayList<Any>>>
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index name                        |
| `collection`       | <pre>String</pre>                                       | Collection name                   |
| `ids`              | `ArrayList<String>`                            | Document IDs                      |
| `waitForRefresh`   | <pre>Boolean</pre>                                      | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |

---

## Return

A `ConcurrentHashMap<String, ArrayList<Any>>` which has a `successes` and `errors` `ArrayList<Any>`:
The `successes` array contains the successfully deleted document IDs.

Each deletion error is an object of the errors array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_id`        | <pre>String</pre>                            | Document ID                      |
| `reason`     | <pre>String</pre>                            | Human readable reason            |

## Usage

<<< ./snippets/m-delete-kotlin.kt

:::
::::