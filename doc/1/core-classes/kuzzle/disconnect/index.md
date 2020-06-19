---
code: true
type: page
title: disconnect
description: Disconnects the SDK from Kuzzle
---

# Disconnect

Closes the current connection to Kuzzle.

Disconnects the SDK from the Kuzzle server using the underlying protocol `disconnect` method. 

:::: tabs
::: tab Java

## Arguments

```java
public void disconnect()
```

## Usage

<<< ./snippets/disconnect-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun disconnect()
```

## Usage

<<< ./snippets/disconnect-kotlin.kt

:::
::::