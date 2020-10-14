FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8080
COPY build/libs/*.jar /usr/local/lib/app.jar
CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-jar", "/usr/local/lib/app.jar"]