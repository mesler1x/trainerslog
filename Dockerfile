FROM eclipse-temurin:21-jre-alpine
COPY target/trainerslog-*-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]