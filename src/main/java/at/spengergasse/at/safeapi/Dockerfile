FROM gradle:8.0.2-jdk17-alpine

WORKDIR /spring

COPY . .

RUN gradle bootJar

ENTRYPOINT ["java", "-jar", ".\\build\\libs\\restsec-0.0.1-SNAPSHOT.jar"]
