#!/bin/bash
set -e

cd /App/project/ && ./gradlew testDebugUnitTest

exec "$@"

