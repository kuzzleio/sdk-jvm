---
code: false
type: page
title: Kotlin
description: Kotlin Getting started
order: 99
---

# Getting Started

In this tutorial you will learn how to install and use the **JVM SDK** for Kuzzle.
This page shows examples of scripts that **store** documents in Kuzzle, and of scripts that subcribe to real-time **notifications** for each new document created.

::: success
Before proceeding, please make sure your system meets the following requirements:

- **Oracle JDK or OpenJDK** version 8 or higher ([OpenJDK installation instructions](https://openjdk.java.net/install/))
- A running Kuzzle Server ([Kuzzle installation guide](/core/2/guides/essentials/installing-kuzzle))

:::


::: info
Having trouble? Get in touch with us on [Discord](http://join.discord.kuzzle.io)!
:::

## Installation

You can find the SDK JARs directly on [bintray](https://bintray.com/kuzzle/maven/sdk-jvm). Download and add them to your classpath.

::: info
The following examples are made to be executed without any IDE.
If you're using Eclipse, IntelliJ or another Java IDE, you need to add the SDK as a project dependency in your classpath.
:::

### Installing for Android projects using gradle

To build the project, add the following lines:

### Maven

```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>sdk-jvm</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### Gradle

```groovy
repositories {
    maven() {
        url  "https://dl.bintray.com/kuzzle/maven" 
    }
}
dependencies {
  compile 'io.kuzzle:sdk-jvm:1.0.0'
}
```

### Ivy

```html
<dependency org='io.kuzzle' name='sdk-jvm' rev='1.0.0'>
  <artifact name='sdk-jvm' ext='pom' ></artifact>
</dependency>
```
## First connection

Initialize a new Java project, create a `GettingStartedFirstConnection.kt` file and start by adding the code below:

<<< ./snippets/firstconnection-kotlin.kt

::: info
If you're not yet familiar with how Kuzzle structures its storage, check our [detailed guide][https://docs.kuzzle.io/core/2/guides/essentials/store-access-data/)
:::

This program initializes the Kuzzle Server storage by creating an index and a collection.
Run the program with the following command:

```bash
$ kotlinc -classpath ./path/to/the/sdk.jar GettingStartedFirstConnection.kt
$ java -classpath .:./path/to/the/sdk.jar GettingStartedFirstConnection
Connected!
Index nyc-open-data created!
Collection yellow-taxi created!
```

You now know how to:

- Instantiate the Kuzzle SDK and connect to a Kuzzle Server using a specific network protocol (here `websocket`)
- Create an index
- Create a collection within an existing index

## Create your first document

Now that you successfully connected to your Kuzzle Server instance, and created an index and a collection, it's time to manipulate documents.

Create a `GettingStartedStorage.kt` file in the playground and add this code:

<<< ./snippets/document-kotlin.kt

As you did before, build and run your program:

```bash
$ kotlinc -classpath ./path/to/the/sdk.jar  GettingStartedStorage.kt
$ java -classpath .:./path/to/the/sdk.jar GettingStartedStorage
Connected!
New document added to the yellow-taxi collection!
```

Many other actions are available to manipulate stored documents. You can check the exhaustive list in the [Document Controller documentation](https://docs.kuzzle.io/sdk/jvm/1/controllers/document).

Now you know how to:

- Store documents in a Kuzzle Server, and access to those

## Subscribe to realtime document notifications (pub/sub)

Time to use Kuzzle's realtime capabilities. Create a new file `GettingStartedRealtime.kt` with the following code:

<<< ./snippets/realtime-kotlin.kt

This program subscribes to changes made to documents with a `license` field set to `B`, within the `yellow-taxi` collection. Whenever a document matching the provided filters changes, a new notification is received from Kuzzle.

Build and run your program:

```bash
$ kotlinc -classpath ./path/to/the/sdk.jar GettingStartedRealtime.kt
$ java -classpath .:./path/to/the/sdk.jar GettingStartedRealtime
Connected!
Successfully subscribing!
New document added to yellow-taxi collection!
New created document notification:

{
  _source={
    birthday=1995-11-27,
    license=B,
    name=John,
    _kuzzle_info={
      createdAt=1605694059151,author=-1
      }
    },
    _id=9PDS2nUBeGNr7nwl8j2Q,
    _version=1
  }

```

You should see the document content as a `ConcurrentHashMap`.

Now, you know how to:

- Create realtime filters
- Subscribe to notifications

## Where do we go from here?

Now that you're more familiar with the JVM SDK, you can dive even deeper to learn how to leverage its full capabilities:

- discover what this SDK has to offer by browsing other sections of this documentation
- learn how to use [pagination strategies](/sdk/jvm/1/core-classes/search-result/next/#pagination-strategies) with the [document:search](/sdk/jvm/1/controllers/document/search/) API action.
- discover other [Kuzzle guides](core/2/guides/essentials/introduction/)