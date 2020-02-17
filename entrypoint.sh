#!/bin/bash
set -e

cd /project && ./gradlew testDebugUnitTest

exec "$@"

