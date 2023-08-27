FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["chmod +x", "./mvnw", "spring-boot:run"]