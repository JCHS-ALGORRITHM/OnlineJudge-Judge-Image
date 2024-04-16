FROM ubuntu:22.04

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Asia/Seoul

RUN apt-get update \
    && apt-get install -y software-properties-common \
    && add-apt-repository ppa:deadsnakes/ppa \
    && apt-get update \
    && apt-get install -y gcc g++ openjdk-21-jdk python3.12 \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \
    && rm -rf /tmp/* \
    && rm -rf /var/tmp/* \
    && ln -s /usr/bin/python3.12 /usr/bin/python3

WORKDIR /app

ENV MONGODB_HOST host.docker.internal

COPY ./build/libs/JudgeSystem.jar ./judge.jar

ENTRYPOINT ["sh", "-c", "java -jar judge.jar"]
