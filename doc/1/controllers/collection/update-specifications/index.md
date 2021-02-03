---
code: true
type: page
title: updateSpecifications
description: Update the validation specifications
---

# updateSpecifications

The updateSpecifications method allows you to create or update the validation specifications for a collection.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

<br/>
:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> updateSpecifications(
      final String index,
      final String collection,
      final Map<String, Object> specifications)
```

<br/>

| Arguments        | Type                                         | Description              |
| ---------------- | -------------------------------------------- | ------------------------ |
| `index`          | <pre>String</pre>                            | Index name               |
| `collection`     | <pre>String</pre>                            | Collection name          |
| `specifications` | <pre>Map<String, Object></pre> | Specifications to update |

### specifications

A `Map<String, Object>` representing the specifications.

It must follow the [Specification Structure](/core/2/guides/advanced/data-validation).

## Returns

Returns a `Map<String, Object>` containing the specifications.

## Usage

<<< ./snippets/update-specifications-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun updateSpecifications(
      index: String,
      collection: String,
      definition: Map<String, Any?>
      ): CompletableFuture<Map<String, Any?>>
```

<br/>

| Arguments        | Type                                         | Description              |
| ---------------- | -------------------------------------------- | ------------------------ |
| `index`          | <pre>String</pre>                            | Index name               |
| `collection`     | <pre>String</pre>                            | Collection name          |
| `specifications` | <pre>Map<String, Any?></pre> | Specifications to update |

### specifications

A `Map<String, Any?>` object representing the specifications.

It must follow the [Specification Structure](/core/2/guides/advanced/data-validation).

## Returns

Returns a `Map<String, Any?>` containing the specifications.


## Usage

<<< ./snippets/update-specifications-kotlin.kt

:::
::::
