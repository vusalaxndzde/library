#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY src ./src
#CMD ["./mvnw", "spring-boot:run"]

#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN ./mvnw -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /home/app/target/library-0.0.1.jar /usr/local/lib/library.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/library.jar"]