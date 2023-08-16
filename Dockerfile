FROM openjdk:17
WORKDIR /app
ADD target/library-0.0.1-SNAPSHOT.jar library.jar
ENTRYPOINT ["java", "-jar", "library.jar"]