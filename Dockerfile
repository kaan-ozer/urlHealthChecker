# 1. Build - Maven ile derleme aşaması
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Run
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Derlenen JAR dosyasını bir önceki aşamadan kopyalanması
COPY --from=build /app/target/*.jar app.jar

# Uygulamanın çalışacağı port
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]