### Kotlin SDK

[![Build Status](https://travis-ci.org/kuzzleio/sdk-jvm.svg?branch=master)](https://travis-ci.org/kuzzleio/sdk-jvm)

Official Kuzzle JVM SDK
======

## About Kuzzle

A backend software, self-hostable and ready to use to power modern apps.

You can access the Kuzzle repository on [Github](https://github.com/kuzzleio/kuzzle)

## SDK Documentation

The complete SDK documentation is available [here](https://docs.kuzzle.io/sdk/jvm/1)

## Protocol used

The JVM SDK implements the websocket protocol and http protocol.

### Build jar

Execute the following snippet to clone the GIT repository, and build the SDK. It will then be available in the "build/" directory

```sh
git clone git@github.com:kuzzleio/sdk-jvm.git
cd sdk-jvm
./gradlew jar
```

You can then find the jars file in build/libs/

## Installation

### Compatibility Matrix

| SDK Version  | Ktor Version | Gradle Version | Java Version |
| :----------: | :----------: | :------------: | :----------: |
| **< 1.2.4**  |  **1.5.2**   |    **6.1**     |  **JDK-8**   |
| **>= 1.2.4** |  **1.6.8**   |    **7.4**     |  **JDK-11**  |

### Maven

```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>sdk-jvm</artifactId>
  <version>1.2.4</version>
  <type>pom</type>
</dependency>
```


### Gradle

```groovy
compile 'io.kuzzle:sdk-jvm:1.2.4'
```

For amd64:

```groovy
compile 'io.kuzzle:sdk-jvm:1.2.4'
```

### Ivy

```html
<dependency org='io.kuzzle' name='sdk-jvm' rev='1.2.4'>
  <artifact name='sdk-jvm' ext='pom' ></artifact>
</dependency>
```

This SDK has 2 jar files that you can use:
* `sdk-jvm-<version>.jar`: this is the fat jar version, containing the SDK and all its dependencies.
* `sdk-jvm-<version>-without-dependencies.jar`: this is the thin jar version of the SDK, without any dependencies included in it.

Depending on your project, you might need one or the other version of the SDK: if you already use some of the dependencies needed by the SDK, then you need to use the thin jar version. Otherwise, you may use the fat jar one.

⚠️ Warning
If you are using the fat jar, you might have duplicate dependencies issues in a Kotlin Android Studio project. Add the following lines to your `build.gradle` file:

```groovy
configurations {
     cleanedAnnotations
     compile.exclude group: 'org.jetbrains'
     compile.exclude group: 'org.jetbrains.kotlin'
 }
```

If you are using the thin jar, make sure to add the following dependencies:

```groovy
    implementation("io.ktor:ktor-client-core:1.6.8")
    implementation("io.ktor:ktor-client-websockets:1.6.8")
    implementation("io.ktor:ktor-client-okhttp:1.6.8")
    implementation("io.ktor:ktor-client-cio:1.6.8")
    implementation("io.ktor:ktor-client-json:1.6.8")
    implementation("io.ktor:ktor-client-gson:1.6.8")
    implementation("io.ktor:ktor-client-serialization:1.6.8")
    implementation("com.google.code.gson:gson:2.9.0")
```