# Собирается из КОРНЯ multi-module проекта:
#   docker build -f markdown-renderer-service/Dockerfile -t markdown-renderer-service .

# ---------- Этап 1: сборка через Maven reactor ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY notes-api/pom.xml notes-api/pom.xml
COPY markdown-renderer-service/pom.xml markdown-renderer-service/pom.xml
COPY frontend-service/pom.xml frontend-service/pom.xml

RUN mvn -B -pl markdown-renderer-service -am dependency:go-offline

COPY markdown-renderer-service/src markdown-renderer-service/src
RUN mvn -B -pl markdown-renderer-service -am package -DskipTests

# ---------- Этап 2: минимальный рантайм-образ ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/markdown-renderer-service/target/*.jar app.jar

EXPOSE 8020
ENTRYPOINT ["java", "-jar", "app.jar"]
