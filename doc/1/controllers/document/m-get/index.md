---
code: true
type: page
title: mGet
description: Gets multiple documents
---

# mGet

Gets multiple documents.

---

:::: tabs
::: tab Java

## Arguments 

```java
public CompletableFuture<Map<String, ArrayList<Object>>> mGet(
      String index,
      String collection,
      ArrayList<String> ids)
throws NotConnectedException, InternalException

```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index name                        |
| `collection`       | <pre>String</pre>                                       | Collection name                   |
| `ids`              | `ArrayList<String>`                            | Document IDs                      |
---

## Return

A `Map<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Object></pre> | Document content                 |
| `_id`        | <pre>String</pre>                            | Document ID                      |
| `_version`   | <pre>Integer</pre>                           | Version of the document in the persistent data storage |

The `errors` array contain the IDs of not found documents.

## Usage

<<< ./snippets/m-get-java.java

:::
::: tab Kotlin

## Arguments 

```kotlin
fun mGet(
      index: String,
      collection: String,
      ids: ArrayList<String>): CompletableFuture<Map<String, ArrayList<Any>>>
```

| Arguments          | Type                                                    | Description                       |
| ------------------ | ------------------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                                       | Index name                        |
| `collection`       | <pre>String</pre>                                       | Collection name                   |
| `ids`              | `ArrayList<String>`                            | Document IDs                      |
---

## Return

A `Map<String, ArrayList<Object>>` which has a `successes` and `errors` `ArrayList<Object>`:
Each created document is an object of the `successes` array with the following properties:

| Property     | Type                                         | Description                      |
|------------- |--------------------------------------------- |--------------------------------- |
| `_source`    | <pre>Map<String, Any?></pre> | Document content                 |
| `_id`        | <pre>String</pre>                            | Document ID                      |
| `_version`   | <pre>Int</pre>                           | Version of the document in the persistent data storage |

The `errors` array contain the IDs of not found documents.

## Usage

<<< ./snippets/m-get-kotlin.kt

:::
::::