#!/bin/bash
set -e

cd /tmp && ./gradlew test

exec "$@"

