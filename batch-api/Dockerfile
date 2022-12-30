FROM openjdk:11-slim as build
MAINTAINER rinjyu <rinjyu@naver.com>

ARG JAR_FILE
COPY ${JAR_FILE} batch-api.jar

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /batch-api.jar)