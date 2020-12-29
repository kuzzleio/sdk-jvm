---
code: true
type: page
title: checkRights
---

# checkRights

<SinceBadge version="Kuzzle 2.8.0"/>
<SinceBadge version="auto-version"/>

Checks if the provided API request can be executed by a user.

:::: tabs
::: tab Java

```java
public CompletableFuture<boolean> checkRights(
  ConcurrentHashMap<String, Object> requestPayload) throws NotConnectedException, InternalException
```

| Property | Type | Description |
|--- |--- |--- |
| `requestPayload` | <pre>ConcurrentHashMap<String, Object></pre> | Contains a [RequestPayload](/core/2/api/payloads/request) |

## `requestPayload`

The [RequestPayload](/core/2/api/payloads/request) must contains at least the following properties:

- `controller`: API controller
- `action`: API action

---

## Returns

Returns a boolean telling whether the provided request would have been allowed or not

## Usage

<<< ./snippets/check-rights-java.java

:::
::: tab Kotlin

```kotlin
 fun checkRights(
      requestPayload: ConcurrentHashMap<String, Any?>): CompletableFuture<Boolean>
```

| Property | Type | Description |
|--- |--- |--- |
| `requestPayload` | <pre>ConcurrentHashMap<String, Any?></pre> | Contains a [RequestPayload](/core/2/api/payloads/request) |

## `requestPayload`

The [RequestPayload](/core/2/api/payloads/request) must contains at least the following properties:

- `controller`: API controller
- `action`: API action

---

## Returns

Returns a boolean telling whether the provided request would have been allowed or not

## Usage

<<< ./snippets/check-rights-kotlin.kt

:::
::::