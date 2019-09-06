#!/usr/bin/env bash

set -e

echo "Stop existing docker containers and remove all its data volumes"
./gradlew composeDown

echo "Start build process..."
./gradlew clean  build

