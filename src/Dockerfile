FROM openjdk:17-jdk-slim

LABEL authors="Diego"

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8082"]