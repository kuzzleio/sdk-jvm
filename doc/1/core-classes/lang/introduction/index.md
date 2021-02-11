---
code: false
type: page
title: Introduction
description: Lang object description
order: 0
---

## Response

`Lang` is an **Enum** class listing the possible query syntax to use for search queries.

## Properties

| Property | Type | Description |
|--- |--- |--- |
| `ELASTICSEARCH` | <pre>Lang</pre> | For Elasticsearch query syntax. |
| `KONCORDE` | <pre>Lang</pre> | For Koncorde query syntax. |

Those enums contain a `lang` value, which is the query language as `String` in lowercase.

```kotlin
print(Lang.ELASTICSEARCH.lang)
/*
"elasticsearch"
*/
```

```kotlin
print(Lang.KONCORDE.lang)
/*
"koncorde"
*/
```