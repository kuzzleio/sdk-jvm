#!/bin/bash

set -e

echo "Wait for Snippets to be built and bundled"
timeout 600 bash -c 'until stat /mnt/build/SnippetTest.java > /dev/null && stat /mnt/build/SnippetTest.kt > /dev/null; do sleep 1; done'
echo "Building SDK"
cd /mnt && ./gradlew jar
echo "Copying SDK"
cp /mnt/build/libs/sdk-jvm-[0-9+].[0-9+].[0-9+]-without-dependencies.jar /mnt/.ci/doc/java-project/libs/sdk-jvm-without-dependencies.jar
cp /mnt/build/libs/sdk-jvm-[0-9+].[0-9+].[0-9+]-without-dependencies.jar /mnt/.ci/doc/kotlin-project/libs/sdk-jvm-without-dependencies.jar
echo "Copying Java Snippets"
cp /mnt/build/SnippetTest.java /mnt/.ci/doc/java-project/src/main/java/SnippetTest.java
echo "Copying Kotlin Snippets"
cp /mnt/build/SnippetTest.kt /mnt/.ci/doc/kotlin-project/src/main/java/SnippetTest.kt
echo "Building Java executable"
cd /mnt/.ci/doc/java-project/ && ./gradlew build
echo "Building Kotlin executable"
cd /mnt/.ci/doc/kotlin-project/ && ./gradlew build
echo "Wait for Kuzzle to be ready"
timeout 600 bash -c 'until stat /tmp/runner_is_ready && curl -f -s -o /dev/null http://kuzzle:7512/_now; do sleep 1; done'