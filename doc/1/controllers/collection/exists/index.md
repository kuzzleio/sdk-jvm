---
code: true
type: page
title: exists
description: Checks if a collection exists
---

# exists

Checks if a collection exists.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Boolean> exists(
      final String index,
      final String collection)
throws NotConnectedException, InternalException

```

---

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |

---

## Return

Returns a boolean.

## Usage

<<< ./snippets/exists.java

:::
::: tab Kotlin

## Arguments

## Usage

:::
::::