#!/bin/bash
set -e

cd /App && ./gradlew test

exec "$@"

