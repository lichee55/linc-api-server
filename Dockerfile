FROM openjdk:11
EXPOSE 8080
ADD /target/api-0.0.1-SNAPSHOT.jar linc-api-server.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "linc-api-server.jar"]