#!/bin/bash
./gradlew build
docker build -t hub.docker.com/vnrg/pp-order-persistence:latest .