FROM ubuntu:22.04

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Asia/Seoul

WORKDIR /app

ENV MONGODB_HOST host.docker.internal

RUN apt-get update && \
    apt-get install -y gcc g++ openjdk-21-jdk pypy3 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

COPY ./build/libs/JudgeSystem.jar ./judge.jar

ENTRYPOINT ["sh", "-c", "java -jar judge.jar"]
