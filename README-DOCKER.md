# Сборка проекта в Docker

## Куда положить файлы

Все файлы из этого архива нужно разместить в **корне** твоего multi-module проекта
(там же, где лежит родительский `pom.xml`):

```
notes-rest-service/                          ← корень проекта
├── pom.xml                                   ← уже есть у тебя
├── docker-compose.yml                        ← из этого архива
├── .dockerignore                             ← из этого архива
├── notes-api/
│   ├── pom.xml                               ← уже есть
│   ├── Dockerfile                            ← из этого архива, положить сюда
│   ├── .env                                  ← остаётся как есть, для локального запуска из IDE
│   └── src/
├── markdown-renderer-service/
│   ├── pom.xml
│   ├── Dockerfile                            ← из этого архива, положить сюда
│   └── src/
└── frontend-service/
    ├── pom.xml
    ├── Dockerfile                            ← из этого архива, положить сюда
    └── src/
```

То есть каждый `Dockerfile` — внутрь соответствующего модуля, а `docker-compose.yml`
и `.dockerignore` — в самый корень репозитория.

## Как это работает

- **4 сервиса на своих портах**: LanguageTool (8010), markdown-renderer-service (8020),
  notes-api (8080), frontend-service (8030) — плюс Postgres (5432).
- Внутри Docker-сети контейнеры обращаются друг к другу **по имени сервиса**
  (`http://notes-api:8080`, `http://languagetool:8010` и т.д.) — это встроенный Docker
  DNS. Это настроено через переменные окружения в `docker-compose.yml`, локальный
  `application.properties` с `localhost` при этом не меняется и продолжает работать
  для запуска из IDE.
- **notes-api/.env не используется при Docker-сборке** (см. `.dockerignore`) — все
  переменные окружения для контейнеров передаются напрямую через `environment:`
  в `docker-compose.yml`. Так секреты из `.env` не попадают внутрь образа.

## Как запустить

Из корня проекта:

```bash
docker-compose up --build
```

Первый запуск займёт время — Maven соберёт каждый модуль внутри отдельного контейнера
(качает зависимости), плюс LanguageTool загрузит языковые модели при первом старте.

После запуска:
- Веб-интерфейс: http://localhost:8030
- Notes API напрямую: http://localhost:8080/api/notes
- Проверить, что LanguageTool поднялся: http://localhost:8010/v2/languages

Остановить всё:
```bash
docker-compose down
```

Остановить и удалить данные Postgres (полная переустановка):
```bash
docker-compose down -v
```

## Как передать другу

Друг должен получить:
1. Весь исходный код репозитория (включая эти Dockerfile/docker-compose.yml)
2. Установленный Docker Desktop (или Docker Engine + Docker Compose на Linux)

Дальше — просто `docker-compose up --build` из корня, ничего больше настраивать
не нужно (пароли БД в `docker-compose.yml` — тестовые, для реального продакшена
их стоит вынести в отдельный `.env`-файл для docker-compose, но для передачи
другу «пощупать проект» — этого достаточно).

## На будущее (необязательные улучшения)

- Сейчас каждый Dockerfile качает Maven-зависимости заново в своём build context —
  можно ускорить через общий build-стейдж с закэшированным `.m2`, но для MVP
  это не критично.
- Пароли Postgres захардкожены в docker-compose.yml для простоты — при реальном
  использовании стоит вынести в `.env` рядом с `docker-compose.yml` и подключить
  через `env_file:`.
