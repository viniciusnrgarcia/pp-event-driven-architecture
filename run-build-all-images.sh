#!/bin/bash
echo 'build pp-auth-server'
sleep 3
cd pp-auth-server
./mvnw clean install -U -DskipTests
docker build -t hub.docker.com/vnrg/pp-auth-server:latest .

echo 'build pp-order-processor'
sleep 3
cd ../pp-order-processor
./gradlew build
docker build -t hub.docker.com/vnrg/pp-order-processor:latest .

echo 'build pp-order-api'
sleep 3
cd ../pp-order-api
./mvnw clean install -U -DskipTests
docker build -t hub.docker.com/vnrg/pp-order-api:latest .

echo 'build pp-order-persistence'
sleep 3
cd ../pp-order-persistence
./gradlew build
docker build -t hub.docker.com/vnrg/pp-order-persistence:latest .

echo 'build pp-order-persistence-index'
sleep 3
cd ../pp-order-persistence-index
./gradlew build
docker build -t hub.docker.com/vnrg/pp-order-persistence-index:latest .

echo 'build pp-order-websocket'
sleep 3
cd pp-order-websocket
./mvnw clean install -U -DskipTests
docker build -t hub.docker.com/vnrg/pp-order-websocket:latest .
