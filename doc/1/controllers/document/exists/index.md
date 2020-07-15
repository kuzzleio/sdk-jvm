---
code: true
type: page
title: exists
description: Checks if a document exists
---

# exists

Checks if a document exists.

---

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Boolean> exists(
      String index,
      String collection,
      String id)
throws NotConnectedException, InternalException
```

---

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id      `         | <pre>String</pre>                            | Document ID |


---

## Return

Returns a boolean.

## Usage

<<< ./snippets/exists-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun exists(
      index: String,
      collection: String,
      id: String): CompletableFuture<Boolean>
```

---

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `id      `         | <pre>String</pre>                            | Document ID |


---

## Return

Returns a boolean.

## Usage

<<< ./snippets/exists-kotlin.kt

:::
::::