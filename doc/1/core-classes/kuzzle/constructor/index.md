---
code: true
type: page
title: Constructor 
description: Creates a new Kuzzle object connected to the backend
order: 100
---

# Constructor

Use this constructor to create a new instance of the SDK.
Each instance represents a different connection to a Kuzzle server with specific options.

:::: tabs
::: tab Java
## Arguments

```java
public Kuzzle(
  AbstractProtocol networkProtocol,
  bool subscribeToSelf
) throws IllegalArgumentException

public Kuzzle(
  AbstractProtocol networkProtocol
) throws IllegalArgumentException
```

<br/>

| Argument   | Type                | Description                       |
| ---------- | ------------------- | --------------------------------- |
| `networkProtocol` | <pre>AbstractProtocol</pre> | Protocol used by the SDK instance |
| `autoResubscribe` | <pre>boolean</pre><br>(`true`) | Class which contains options |

## networkProtocol

The protocol used to connect to the Kuzzle instance.
It can be one of the following available protocols:

- [WebSocket](/sdk/java/3/protocols/websocket)

## Return

The `Kuzzle` SDK instance.

## Throws

Can throw an [IllegalArgumentException](/sdk/java/3/exceptions/illegal-argument-exception)

## Usage

<<< ./snippets/constructor-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun Kuzzle(protocol: AbstractProtocol, autoResubscribe: Boolean = true)
```

<br/>

| Argument   | Type                | Description                       |
| ---------- | ------------------- | --------------------------------- |
| `networkProtocol` | <pre>AbstractProtocol</pre> | Protocol used by the SDK instance |
| `autoResubscribe` | <pre>Boolean</pre><br>(`true`) | Class which contains options |

## networkProtocol

The protocol used to connect to the Kuzzle instance.
It can be one of the following available protocols:

- [WebSocket](/sdk/jvm/1/protocols/websocket)

## Return

The `Kuzzle` SDK instance.

## Usage

<<< ./snippets/constructor-kotlin.kt

:::
::::