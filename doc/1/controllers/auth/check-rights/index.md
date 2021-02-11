---
code: true
type: page
title: checkRights
description: Using the current authentication information, verify if the network connection can execute the required API request.
---

# checkRights

<SinceBadge version="Kuzzle 2.8.0"/>
<SinceBadge version="1.1.0"/>

Using the current authentication information, verify if the network connection can execute the required API request.

:::: tabs
::: tab Java

```java
public CompletableFuture<boolean> checkRights(
  Map<String, Object> requestPayload) throws NotConnectedException, InternalException
```

| Property | Type | Description |
|--- |--- |--- |
| `requestPayload` | <pre>Map<String, Object></pre> | Contains a [RequestPayload](/core/2/api/payloads/request) |

## `requestPayload`

The [RequestPayload](/core/2/api/payloads/request) must contain at least the following properties:

- `controller`: API controller
- `action`: API action

---

## Returns

Returns a boolean indicating whether the provided request would have been allowed or not

## Usage

<<< ./snippets/check-rights-java.java

:::
::: tab Kotlin

```kotlin
 fun checkRights(
      requestPayload: Map<String, Any?>): CompletableFuture<Boolean>
```

| Property | Type | Description |
|--- |--- |--- |
| `requestPayload` | <pre>Map<String, Any?></pre> | Contains a [RequestPayload](/core/2/api/payloads/request) |

## `requestPayload`

The [RequestPayload](/core/2/api/payloads/request) must contain at least the following properties:

- `controller`: API controller
- `action`: API action

---

## Returns

Returns a boolean indicating whether the provided request would have been allowed or not

## Usage

<<< ./snippets/check-rights-kotlin.kt

:::
::::
