FROM eclipse-temurin:21-jre-alpine
LABEL authors="mesler, flashouti"
COPY /target/trainerslog-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]
