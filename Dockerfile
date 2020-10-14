FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8080
ADD build/libs/digital-farm-0.0.1-SNAPSHOT.jar digital-farm.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/digital-farm.jar"]