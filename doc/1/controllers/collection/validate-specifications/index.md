---
code: true
type: page
title: validateSpecifications
description: Validate specifications format
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store or modify the existing specification.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

<br/>

:::: tabs
::: tab Java

## Arguments

```java
  public CompletableFuture<Map<String, Object>> validateSpecifications(
      final String index,
      final String collection,
      final Map<String, Object> specifications)
```

<br/>

| Arguments        | Type                  | Description                |
| ---------------- | --------------------- | -------------------------- |
| `index`          | `String`              | Index name                 |
| `collection`     | `String`              | Collection name            |
| `specifications` | `Map<String, Object>` | Specifications to validate |

### specifications

A `Map<String, Object>` representing the specifications.

This object must follow the [Specification Structure](/core/2/guides/advanced/data-validation).

## Returns

Returns a `Map<String, Object>` which contains information about the specifications validity.

It contains the following properties:

| Property      | Type                | Description                  |
| ------------- | ------------------- | ---------------------------- |
| `valid`       | `Boolean`           | Specifications validity      |
| `details`     | `ArrayList<String>` | Specifications errors        |
| `description` | `String`            | Global description of errors |

## Usage

\<\<\< ./snippets/validate-specifications-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun validateSpecifications(
    index: String,
    collection: String,
    specifications: Map<String, Any>?
  ): CompletableFuture<Map<String, Any?>>
```

<br/>

| Arguments        | Type                | Description                |
| ---------------- | ------------------- | -------------------------- |
| `index`          | `String`            | Index name                 |
| `collection`     | `String`            | Collection name            |
| `specifications` | `Map<String, Any?>` | Specifications to validate |

### specifications

A `Map<String, Any?>` representing the specifications.

This object must follow the [Specification Structure](/core/2/guides/advanced/data-validation).

## Returns

Returns a `Map<String, Any?>` which contains information about the specifications validity.

It contains the following properties:

| Property      | Type                | Description                  |
| ------------- | ------------------- | ---------------------------- |
| `valid`       | `Boolean`           | Specifications validity      |
| `details`     | `ArrayList<String>` | Specifications errors        |
| `description` | `String`            | Global description of errors |

## Usage

\<\<\< ./snippets/validate-specifications-kotlin.kt

:::
::::
