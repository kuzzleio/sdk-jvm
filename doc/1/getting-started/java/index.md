---
code: false
type: page
title: Java
description: Java Getting started
order: 99
---

# Getting Started

In this tutorial you will learn how to install and use the **JVM SDK** for Kuzzle.
This page shows examples of scripts that **store** documents in Kuzzle, and of scripts that subcribe to real-time **notifications** for each new document created.

::: success
Before proceeding, please make sure your system meets the following requirements:

- **Oracle JDK or OpenJDK** version 8 or higher ([OpenJDK installation instructions](https://openjdk.java.net/install/))
- A running Kuzzle Server ([Kuzzle installation guide](/core/2/guides/getting-started/run-kuzzle))

:::


::: info
Having trouble? Get in touch with us on [Discord](http://join.discord.kuzzle.io)!
:::

## Installation

You can find the SDK JARs directly on [bintray](https://bintray.com/kuzzle/maven/sdk-jvm). Download and add them to your classpath.

This SDK has 2 jar files that you can use:
* `sdk-jvm-<version>.jar`: this is the fat jar version, containing the SDK and all its dependencies.
* `sdk-jvm-<version>-without-dependencies.jar`: this is the thin jar version of the SDK, without any dependencies included in it.

Depending on your project, you might need one or the other version of the SDK: if you already use some of the dependencies needed by the SDK, then you need to use the thin jar version. Otherwise, you may use the fat jar one.

If you are using the thin jar, make sure to add the following dependencies:

```groovy
    implementation("io.ktor:ktor-client-websockets:1.5.2")
    implementation("io.ktor:ktor-client-okhttp:1.5.2")
    implementation("io.ktor:ktor-client-cio:1.5.2")
    implementation("io.ktor:ktor-client-json:1.5.2")
    implementation("io.ktor:ktor-client-gson:1.5.2")
    implementation("io.ktor:ktor-client-serialization:1.5.2")
    implementation("com.google.code.gson:gson:2.8.5")
```

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
  <version>1.2.2</version>
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
  compile 'io.kuzzle:sdk-jvm:1.2.2'
}
```

### Ivy

```html
<dependency org='io.kuzzle' name='sdk-jvm' rev='1.2.2'>
  <artifact name='sdk-jvm' ext='pom' ></artifact>
</dependency>
```

## First connection

Initialize a new Java project, create a `GettingStartedFirstConnection.java` file and start by adding the code below:

<<< ./snippets/firstconnection-java.java

::: info
If you're not yet familiar with how Kuzzle structures its storage, check our [detailed guide](/core/2/guides/main-concepts/data-storage)
:::

This program initializes the Kuzzle Server storage by creating an index and a collection.
Run the program with the following command:

```bash
$ javac -classpath ./path/to/the/sdk.jar GettingStartedFirstConnection.java
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

Create a `GettingStartedStorage.java` file in the playground and add this code:

<<< ./snippets/document-java.java

As you did before, build and run your program:

```bash
$ javac -classpath ./path/to/the/sdk.jar  GettingStartedStorage.java
$ java -classpath .:./path/to/the/sdk.jar GettingStartedStorage
Connected!
New document added to the yellow-taxi collection!
```

Many other actions are available to manipulate stored documents. You can check the exhaustive list in the [Document Controller documentation](https://docs.kuzzle.io/sdk/jvm/1/controllers/document).

Now you know how to:

- Store documents in a Kuzzle Server, and access to those

## Subscribe to realtime document notifications (pub/sub)

Time to use Kuzzle's realtime capabilities. Create a new file `GettingStartedRealtime.java` with the following code:

<<< ./snippets/realtime-java.java

This program subscribes to changes made to documents with a `license` field set to `B`, within the `yellow-taxi` collection. Whenever a document matching the provided filters changes, a new notification is received from Kuzzle.

Build and run your program:

```bash
$ javac -classpath ./path/to/the/sdk.jar GettingStartedRealtime.java
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

You should see the document content as a `Map`.

Now, you know how to:

- Create realtime filters
- Subscribe to notifications

## Where do we go from here?

Now that you're more familiar with the JVM SDK, you can dive even deeper to learn how to leverage its full capabilities:

- discover what this SDK has to offer by browsing other sections of this documentation
- learn how to use [pagination strategies](/sdk/jvm/1/core-classes/search-result/next/#pagination-strategies) with the [document:search](/sdk/jvm/1/controllers/document/search/) API action.
- discover other [Kuzzle guides](core/2/guides/introduction/what-is-kuzzle/)
