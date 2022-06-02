FROM gradle:7.4.2-jdk8 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-oraclelinux7
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/exchange_rate_checker-0.0.1-SNAPSHOT.jar /app/application.jar

ENTRYPOINT ["java", "-jar", "/app/application.jar"]