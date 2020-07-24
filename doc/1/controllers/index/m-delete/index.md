---
code: true
type: page
title: mDelete
description: Deletes multiple indexes.
---

# mDelete

Deletes multiple indexes.

:::: tabs
::: tab Java

## Arguments

```java
CompletableFuture<ArrayList<String>> mDelete(ArrayList<String> indexes) 
  throws NotConnectedException, InternalException
```

| Argument  | Type              | Description           |
|-----------|-------------------|-----------------------|
| `indexes` | <pre>ArrayList<String></pre> | List of indexes names |

## Return

Returns an `ArrayList<String>` containing the list of indexes names deleted.

## Usage

<<< ./snippets/mDelete-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun mDelete(indexes: ArrayList<String>): CompletableFuture<ArrayList<String>>
```

| Argument  | Type              | Description           |
|-----------|-------------------|-----------------------|
| `indexes` | <pre>ArrayList<String></pre> | List of indexes names |

## Return

Returns an `ArrayList<String>` containing the list of indexes names deleted.

## Usage

<<< ./snippets/mDelete-kotlin.kt

:::
::::
