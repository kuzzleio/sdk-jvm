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

The JVM SDK implements the websocket protocol.

### Build jar

Execute the following snippet to clone the GIT repository, and build the SDK. It will then be available in the "build/" directory

```sh
git clone git@github.com:kuzzleio/sdk-jvm.git
cd sdk-jvm
./gradlew jar
```

You can then find the jars file in build/libs/

## Installation

### Bintray repository

The SDK is available for both x86 and amd64 architectures on bintray:

https://bintray.com/kuzzle/maven

### Maven

```xml
<dependency>
  <groupId>io.kuzzle</groupId>
  <artifactId>sdk-jvm</artifactId>
  <version>1.1.0</version>
  <type>pom</type>
</dependency>
```


### Gradle

```groovy
compile 'io.kuzzle:sdk-jvm:1.1.0'
```

For amd64:

```groovy
compile 'io.kuzzle:sdk-jvm:1.1.0'
```

### Ivy

```html
<dependency org='io.kuzzle' name='sdk-jvm' rev='1.1.0'>
  <artifact name='sdk-jvm' ext='pom' ></artifact>
</dependency>
```

::: warning
If you have duplicates dependencies issues, you should either add those lines in your application `gradle.build file`

```groovy
configurations {
     cleanedAnnotations
     compile.exclude group: 'org.jetbrains'
     compile.exclude group: 'org.jetbrains.kotlin'
}
```

Or you can use our jar dependencies less, and add those dependencies in your application:

```groovy
    implementation("io.ktor:ktor-client-websockets:1.5.2")
    implementation("io.ktor:ktor-client-okhttp:1.5.2")
    implementation("io.ktor:ktor-client-cio:1.5.2")
    implementation("io.ktor:ktor-client-json:1.5.2")
    implementation("io.ktor:ktor-client-gson:1.5.2")
    implementation("io.ktor:ktor-client-serialization:1.5.2")
    implementation("com.google.code.gson:gson:2.8.5")
```

:::