#!/bin/bash
set -e

cd /App && ./gradlew 

exec "$@"

