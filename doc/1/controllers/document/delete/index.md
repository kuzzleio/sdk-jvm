---
code: true
type: page
title: delete
description: Deletes a document
---

# delete

Deletes a document.

---

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> delete(
      String index,
      String collection,
      String id)
throws NotConnectedException, InternalException

public CompletableFuture<Map<String, Object>> delete(
      String index,
      String collection,
      String id,
      Boolean waitForRefresh)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id      `         | <pre>String</pre>                            | Document ID |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `Map` which has the following property:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_id`        | <pre>String</pre>            | ID of the deleted document                       |

## Usage

<<< ./snippets/delete-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun delete(
      index: String,
      collection: String,
      id: String?,
      waitForRefresh: Boolean? = null): CompletableFuture<Map<String, Any?>>
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id      `         | <pre>String</pre>                            | Document ID |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|

---

## Return

A `Map` which has the following property:

| Property     | Type                         | Description                      |
|------------- |----------------------------- |--------------------------------- |
| `_id`        | <pre>String</pre>            | ID of the deleted document                       |

## Usage

<<< ./snippets/delete-kotlin.kt

:::
::::