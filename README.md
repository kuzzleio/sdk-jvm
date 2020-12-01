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
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```


### Gradle

```groovy
compile 'io.kuzzle:sdk-jvm:1.0.0'
```

For amd64:

```groovy
compile 'io.kuzzle:sdk-jvm:1.0.0'
```

### Ivy

```html
<dependency org='io.kuzzle' name='sdk-jvm' rev='1.0.0'>
  <artifact name='sdk-jvm' ext='pom' ></artifact>
</dependency>
```