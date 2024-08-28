FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar devopsgpt.jar
ENTRYPOINT ["java","-jar","/devopsgpt.jar"]
