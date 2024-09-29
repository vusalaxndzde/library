FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar library.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library.jar", "--spring.profiles.active=dev"]