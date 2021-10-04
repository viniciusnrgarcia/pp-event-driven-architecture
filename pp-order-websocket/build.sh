#!/bin/bash
./mvnw clean install -U -DskipTests
docker build -t hub.docker.com/vnrg/pp-order-websocket:latest .