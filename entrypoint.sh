#!/bin/bash
set -e

cd /tmp && ls -la 

exec "$@"

