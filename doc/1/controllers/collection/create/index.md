---
code: true
type: page
title: create
description: Creates a new collection
---

# create

Creates a new [collection](/core/2/guides/essentials/store-access-data) in Kuzzle via the persistence engine, in the provided index.

You can also provide optional data mappings that allow you to exploit the full capabilities of our
persistent data storage layer, [ElasticSearch](https://www.elastic.co/elastic-stack) (check here the [mappings capabilities of ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/mapping.html)).

This method will only update the mappings and/or the settings if the collection already exists.

<br/>

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<Void> create(
      final String index,
      final String collection,
      final ConcurrentHashMap<String, Object> definition)
throws NotConnectedException, InternalException

public CompletableFuture<Void> create(
      final String index,
      final String collection)
throws NotConnectedException, InternalException
```

| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `definition`          | <pre>ConcurrentHashMap<String, Object></pre> | Describes the collection mappings and the ES index settings |

---

### definition

An object containings:
 - [collection mappings](/core/2/guides/essentials/database-mappings).
 - Elasticsearch [index settings](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/index-modules.html#index-modules-settings)
The mappings must have a root field `properties` that contain the mapping definition:

```java
{
  "mappings"= {
    "properties"= {
      "field1"= { "type"= "text" },
      "field2"= {
        "properties"= {
          "nestedField"= { "type"= "keyword" }
        }
      }
    }    
  },
  "settings"= {

  }
};
```

## Usage

<<< ./snippets/create-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun create(
      index: String,
      collection: String,
      definition: ConcurrentHashMap<String, Any>?
    ): CompletableFuture<Void>
```
| Arguments          | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `index`            | <pre>String</pre>                            | Index                             |
| `collection`       | <pre>String</pre>                            | Collection                        |
| `definition`       | <pre>ConcurrentHashMap<String, Any>?</pre>   | Describes the collection mappings and the ES index settings |

---

## Usage

<<< ./snippets/create-kotlin.kt

:::
::::
