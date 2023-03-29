FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY build/libs/news-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "news-0.0.1-SNAPSHOT.jar"]
