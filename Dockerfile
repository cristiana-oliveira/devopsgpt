FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar dissertation.jar
ENTRYPOINT ["java","-jar","/dissertation.jar"]
