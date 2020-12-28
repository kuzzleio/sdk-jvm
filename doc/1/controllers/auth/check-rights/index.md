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
public CompletableFuture<ConcurrentHashMap<String, Object>> checkRights(
  String kuid, ConcurrentHashMap<String, Object> requestPayload) throws NotConnectedException, InternalException
```

| Property | Type | Description |
|--- |--- |--- |
| `kuid` | <pre>String</pre> | User [kuid](/core/2/guides/main-concepts/authentication#kuzzle-user-identifier-kuid) |
| `requestPayload` | <pre>ConcurrentHashMap<String, Object></pre> | Contains a [RequestPayload](/core/2/api/payloads/request) |

## Body properties

The body must contain a [RequestPayload](/core/2/api/payloads/request) with at least the following properties:

- `controller`: API controller
- `action`: API action

---

## Returns

The returned result contains the following property:

- `allowed`: a boolean telling whether the provided request would have been allowed or not

```java
{
  "allowed"=true
}
```

## Usage

<<< ./snippets/check-rights-java.java

:::
::: tab Kotlin

```kotlin
 fun checkRights(
      kuid: String,
      requestPayload: ConcurrentHashMap<String, Any?>): CompletableFuture<ConcurrentHashMap<String, Any?>>
```

| Property | Type | Description |
|--- |--- |--- |
| `kuid` | <pre>String</pre> | User [kuid](/core/2/guides/main-concepts/authentication#kuzzle-user-identifier-kuid) |
| `requestPayload` | <pre>ConcurrentHashMap<String, Any?></pre> | Contains a [RequestPayload](/core/2/api/payloads/request) |

## Body properties

The body must contain a [RequestPayload](/core/2/api/payloads/request) with at least the following properties:

- `controller`: API controller
- `action`: API action

---

## Returns

The returned result contains the following property:

- `allowed`: a boolean telling whether the provided request would have been allowed or not

```kotlin
{
  "allowed"=true
}
```

## Usage

<<< ./snippets/check-rights-kotlin.kt

:::
::::