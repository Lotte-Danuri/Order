FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/Order-0.0.1-SNAPSHOT.jar OrderServer.jar
ENTRYPOINT ["java","-jar","OrderServer.jar"]