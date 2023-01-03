#!/bin/bash

set -e

if [ ${1: -3} == ".kt" ]
then
  cd /mnt/.ci/doc/kotlin-project/ && java -classpath 'libs/sdk-jvm-without-dependencies.jar:' -jar build/libs/project-1.jar $2
else
  cd /mnt/.ci/doc/java-project/ && java -classpath 'libs/sdk-jvm-without-dependencies.jar:' -jar build/libs/project-1.jar $2
fi
