# Usar a imagem oficial do Maven para compilar o projeto
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usar a imagem oficial do OpenJDK para rodar o projeto
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/tasksandnews-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
