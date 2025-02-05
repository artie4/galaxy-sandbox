#!/usr/bin/env sh

podman-compose -f docker-compose-postgres.yml up -d

sleep 5

podman-compose -f docker-compose.yml up -d
