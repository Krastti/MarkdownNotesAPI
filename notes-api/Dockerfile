# Собирается из КОРНЯ multi-module проекта:
#   docker build -f notes-api/Dockerfile -t notes-api .
# (docker-compose.yml уже настроен так, что context: . и dockerfile указывает сюда)

# ---------- Этап 1: сборка через Maven reactor ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Сначала копируем только pom.xml всех модулей — так Docker закэширует слой
# с зависимостями, и повторные сборки при изменении только исходников (без
# изменения pom.xml) не будут заново качать все зависимости из Maven Central.
COPY pom.xml .
COPY notes-api/pom.xml notes-api/pom.xml
COPY markdown-renderer-service/pom.xml markdown-renderer-service/pom.xml
COPY frontend-service/pom.xml frontend-service/pom.xml

# -pl notes-api -am — собрать только модуль notes-api и его зависимости
# (-am = also make, обязательно для multi-module reactor).
# Maven'у всё равно нужно видеть pom.xml остальных модулей (они перечислены
# в <modules> родителя), даже если их исходники сюда ещё не скопированы.
RUN mvn -B -pl notes-api -am dependency:go-offline

# Теперь копируем исходники именно notes-api и собираем jar.
COPY notes-api/src notes-api/src
RUN mvn -B -pl notes-api -am package -DskipTests

# ---------- Этап 2: минимальный рантайм-образ ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Копируем только собранный jar из этапа сборки — финальный образ не содержит
# ни Maven, ни исходников, ни кэша зависимостей — только JRE и готовое приложение.
COPY --from=build /app/notes-api/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
