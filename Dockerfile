FROM gradle:8.0.2-jdk17-alpine as build
WORKDIR /spring

COPY . .

RUN gradle bootJAR -x test

FROM eclipse-temurin:17-alpine

COPY --from=build /spring/build/libs/demo-0.0.1-SNAPSHOT.jar /spring.jar

ENTRYPOINT ["java", "-jar", "/spring.jar"]