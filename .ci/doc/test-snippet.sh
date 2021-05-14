#!/bin/bash

set -e

if [ ${1: -3} == ".kt" ]
then
  cp /mnt/build/libs/sdk-jvm-[0-9+].[0-9+].[0-9+]-without-dependencies.jar /mnt/.ci/doc/kotlin-project/libs/ && cp $1 /mnt/.ci/doc/kotlin-project/src/main/java/SnippetTest.kt && cd /mnt/.ci/doc/kotlin-project/ && ./gradlew build && java -classpath 'libs/sdk-jvm-1.2.1-without-dependencies.jar:' -jar build/libs/project-1.jar
else
  cp /mnt/build/libs/sdk-jvm-[0-9+].[0-9+].[0-9+]-without-dependencies.jar /mnt/.ci/doc/java-project/libs/ && cp $1 /mnt/.ci/doc/java-project/src/main/java/SnippetTest.java && cd /mnt/.ci/doc/java-project/ && ./gradlew build && java -classpath 'libs/sdk-jvm-1.2.1-without-dependencies.jar:' -jar build/libs/project-1.jar
fi
