---
code: true
type: page
title: update
description: Update the collection mapping
---

# update

<SinceBadge version="Kuzzle 2.1.0" />

You can define the collection [dynamic mappings policy](/core/2/guides/main-concepts/data-storage#mappings-dynamic-policy) by setting the `dynamic` field to the desired value.

You can define [collection additional metadata](/core/2/guides/main-concepts/data-storage#mappings-metadata) within the `_meta` root field.

<SinceBadge version="Kuzzle 2.2.0" />

You can also provide Elasticsearch [index settings](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/index-modules.html#index-modules-settings) when creating a new collection.

<br/>
:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Map<String, Object>> update(
      final String index,
      final String collection,
      final Map<String, Object> definition)

```

<br/>

| Arguments    | Type              | Description                                                 |
|--------------|-------------------|-------------------------------------------------------------|
| `index`      | <pre>String</pre> | Index name                                                  |
| `collection` | <pre>String</pre> | Collection name                                             |
| `definition` | <pre>Map<String, Object></pre> | Describes the collection mappings and the ES index settings |

### definition

An object containing:
 - [collection mappings](/core/2/guides/main-concepts/data-storage).
 - Elasticsearch [index settings](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/index-modules.html#index-modules-settings)


```java
{
  "mappings"={
    "properties"={
      "field1"={ "type"="text" },
      "field2"={
        "properties"={
          "nestedField"={ "type": "keyword" }
        }
      }
    }    
  },
  "settings"={
    // index settings (e.g. analyzers)
  }
};
```

## Usage

<<< ./snippets/update-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun update(
    index: String,
    collection: String,
    definition: Map<String, Any?>
  ): CompletableFuture<Void>
```

<br/>

| Arguments    | Type              | Description                                                 |
|--------------|-------------------|-------------------------------------------------------------|
| `index`      | <pre>String</pre> | Index name                                                  |
| `collection` | <pre>String</pre> | Collection name                                             |
| `definition` | <pre>Map<String, Any?></pre> | Describes the collection mappings and the ES index settings |

### definition

An object containing:
 - [collection mappings](/core/2/guides/main-concepts/data-storage).
 - Elasticsearch [index settings](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/index-modules.html#index-modules-settings)


```kotlin
{
  "mappings"={
    "properties"={
      "field1"={ "type"="text" },
      "field2"={
        "properties"={
          "nestedField"={ "type": "keyword" }
        }
      }
    }    
  },
  "settings"={
    // index settings (e.g. analyzers)
  }
};
```

## Usage

<<< ./snippets/update-kotlin.kt

:::
::::