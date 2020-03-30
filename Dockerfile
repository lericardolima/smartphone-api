FROM gradle:6.3-jdk AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/smartphone-api-1.1.0-SNAPSHOT.war /app/smartphone-api.war
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app/smartphone-api.war"]
