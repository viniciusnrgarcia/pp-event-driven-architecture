FROM openjdk:11-jre-slim as builder
MAINTAINER Vinicius Garcia <viniciusnrgarcia@gmail.com>

VOLUME ["/tmp/logs"]

LABEL SERVICE_NAME=pp-order-persistence

ENV JAVA_TOOL_OPTIONS="-Xms128m -Xmx256m"

RUN sh -c 'touch /app.jar'
RUN apt update && apt install curl -y

FROM builder
ADD build/libs/pp-order-persistence-0.0.1.jar app.jar

ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8080