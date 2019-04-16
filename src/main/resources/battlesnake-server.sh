#!/usr/bin/env bash

docker run --rm --name battlesnake-server -p3004:3004 -p3005:3005 -p3010:3010  battlesnakeio/engine engine dev