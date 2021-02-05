---
code: true
type: page
title: subscribe
description: Subscribes to real-time notifications.
---

# Subscribe

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications](/core/2/api/payloads/notifications), sent to you in real-time by Kuzzle.

:::: tabs
::: tab Java

## Arguments

```java
public CompletableFuture<String> subscribe(
  String index, 
  String collection, 
  Map<String, Object> filters,
  String scope,
  String users,
  boolean subscribeToSelf,
  Map<String, Object> volatiles,
  NotificationHandler handler, 
) throws NotConnectedException, InternalException

public CompletableFuture<String> subscribe(
  String index, 
  String collection, 
  Map<String, Object> filters,
  String scope,
  String users,
  boolean subscribeToSelf,
  NotificationHandler handler, 
) throws NotConnectedException, InternalException

public CompletableFuture<String> subscribe(
  String index, 
  String collection, 
  Map<String, Object> filters,
  String scope,
  String users,
  NotificationHandler handler, 
) throws NotConnectedException, InternalException

public CompletableFuture<String> subscribe(
  String index, 
  String collection, 
  Map<String, Object> filters,
  String scope,
  NotificationHandler handler, 
) throws NotConnectedException, InternalException

public CompletableFuture<String> subscribe(
  String index, 
  String collection, 
  Map<String, Object> filters,
  NotificationHandler handler, 
) throws NotConnectedException, InternalException
```

| Argument     | Type                                    | Description                                                                                                    |
|--------------|-----------------------------------------|----------------------------------------------------------------------------------------------------------------|
| `index`      | <pre>String</pre>                       | Index name                                                                                                     |
| `collection` | <pre>String</pre>                       | Collection name                                                                                                |
| `filters`    | <pre>Map<String, Object></pre>                      | Map representing a set of filters following [Koncorde syntax](/core/2/api/koncorde-filters-syntax) |
| `handler`   | <pre>NotificationHandler</pre>          | Handler function to handle notifications                                                                      |
| `scope`    | <pre>String</pre><br>(`all`) | Subscribes to document entering or leaving the scope<br>Possible values: all, in, out, none |
| `users`    | <pre>String</pre><br>(`none`) | Subscribes to users entering or leaving the room<br>Possible values: all, in, out, none |
| `subscribeToSelf`    | <pre>boolean</pre><br>(`true`) | Subscribes to notifications fired by our own queries |
| `volatile`    | <pre>Map<String, Object></pre><br>(`{}`) | Map representing subscription information, used in user join/leave notifications |

### handler

Handler function that will be called each time a new notification is received.
The handler will receive a [Response](/sdk/jvm/1/core-classes/response) as its only argument.

## Return

The room ID.

## Usage

Simple subscription to document notifications_

<<< ./snippets/document-notifications-java.java

Subscription to document notifications with scope option_

<<< ./snippets/document-notifications-leave-scope-java.java

:::
::: tab Kotlin

## Arguments

```kotlin
fun subscribe(
  index: String?,
  collection: String?,
  filters: Map<String, Any>,
  scope: String = "all",
  users: String = "all",
  subscribeToSelf: Boolean = true,
  volatiles: Map<String?, Any?> = HashMap(),
  handler: (Response) -> Unit): CompletableFuture<String>
```

| Argument     | Type                                    | Description                                                                                                    |
|--------------|-----------------------------------------|----------------------------------------------------------------------------------------------------------------|
| `index`      | <pre>String</pre>                       | Index name                                                                                                     |
| `collection` | <pre>String</pre>                       | Collection name                                                                                                |
| `filters`    | <pre>Map<String, Object></pre>                      | Map representing a set of filters following [Koncorde syntax](/core/2/api/koncorde-filters-syntax) |
| `handler`   | <pre>NotificationHandler</pre>          | Handler function to handle notifications                                                                      |
| `scope`    | <pre>String</pre><br>(`all`) | Subscribes to document entering or leaving the scope<br>Possible values: all, in, out, none |
| `users`    | <pre>String</pre><br>(`none`) | Subscribes to users entering or leaving the room<br>Possible values: all, in, out, none |
| `subscribeToSelf`    | <pre>boolean</pre><br>(`true`) | Subscribes to notifications fired by our own queries |
| `volatile`    | <pre>Map<String, Object></pre><br>(`{}`) | Map representing subscription information, used in user join/leave notifications |

### handler

Handler function that will be called each time a new notification is received.
The handler will receive a [Response](/sdk/jvm/1/core-classes/response) as its only argument.

## Return

The room ID.

## Usage

Simple subscription to document notifications_

<<< ./snippets/document-notifications-kotlin.kt

Subscription to document notifications with scope option_

<<< ./snippets/document-notifications-leave-scope-kotlin.kt

:::
::::