## Language / Язык

- [Русский](#notes-rest-service-ru)
- [English](#notes-rest-service-en)

---

<a id="notes-rest-service-ru"></a>
# Notes REST Service

**Репозиторий проекта:** https://github.com/Krastti/MarkdownNotesAPI

Pet-проект для заметок: загрузка markdown-файлов, проверка грамматики (LanguageTool),
хранение заметок и рендер их в HTML (flexmark-java). Состоит из трёх Spring Boot
сервисов, разворачивается через Docker Compose.

## Состав проекта

| Сервис                     | Порт по умолчанию | Назначение                                   |
|-----------------------------|--------------------|-----------------------------------------------|
| `notes-api`                 | 8080               | CRUD заметок, upload, grammar-check, render   |
| `markdown-renderer-service` | 8020               | Рендер markdown → HTML (flexmark-java)        |
| `frontend-service`          | 8030               | Веб-интерфейс на Thymeleaf                    |
| `postgres`                  | 5432               | База данных                                   |
| `languagetool`               | 8010               | Проверка грамматики (self-hosted)             |

## Требования

- Docker и Docker Compose
- Git

## 1. Клонирование репозитория

```bash
git clone https://github.com/Krastti/MarkdownNotesAPI
```

## 2. Настройка переменных окружения

В корне проекта создайте файл `.env` на основе `.env.example`:

Заполните значения, например:

```dotenv
DB_NAME=notes
DB_USERNAME=notes_user
DB_PASSWORD=notes_password

DB_PORT=5432
LANGUAGETOOL_PORT=8010
MARKDOWN_RENDERER_PORT=8020
NOTES_API_PORT=8080
FRONTEND_PORT=8030
```

## 3. Сборка и запуск

Из корня проекта:

```bash
docker compose up --build
```

## 4. Открыть приложение

В браузере откройте:

```
http://localhost:8030/notes
```

Вы увидите список заметок. Через интерфейс можно:
- создать заметку вручную
- загрузить markdown-файл
- отредактировать или удалить заметку

## Остановка

```bash
docker compose down
```

Чтобы удалить и данные Postgres (полный сброс БД):

```bash
docker compose down -v
```

---

<a id="notes-rest-service-en"></a>
# Notes REST Service

**Project repository:** https://github.com/Krastti/MarkdownNotesAPI

A pet project for notes: uploading markdown files, grammar checking (LanguageTool),
storing notes and rendering them to HTML (flexmark-java). Consists of three Spring Boot
services, deployed via Docker Compose.

## Project composition

| Service                     | Default port | Purpose                                        |
|-----------------------------|---------------|-------------------------------------------------|
| `notes-api`                 | 8080          | Notes CRUD, upload, grammar-check, render       |
| `markdown-renderer-service` | 8020          | Renders markdown → HTML (flexmark-java)         |
| `frontend-service`          | 8030          | Web interface built with Thymeleaf              |
| `postgres`                  | 5432          | Database                                        |
| `languagetool`              | 8010          | Grammar checking (self-hosted)                  |

## Requirements

- Docker and Docker Compose
- Git

## 1. Clone the repository

```bash
git clone https://github.com/Krastti/MarkdownNotesAPI
```

## 2. Configure environment variables

In the project root, create a `.env` file based on `.env.example`:

Fill in the values, for example:

```dotenv
DB_NAME=notes
DB_USERNAME=notes_user
DB_PASSWORD=notes_password

DB_PORT=5432
LANGUAGETOOL_PORT=8010
MARKDOWN_RENDERER_PORT=8020
NOTES_API_PORT=8080
FRONTEND_PORT=8030
```

## 3. Build and run

From the project root:

```bash
docker compose up --build
```

## 4. Open the application

Open in your browser:

```
http://localhost:8030/notes
```

You'll see a list of notes. Through the interface you can:
- create a note manually
- upload a markdown file
- edit or delete a note

## Stopping

```bash
docker compose down
```

To also remove Postgres data (full DB reset):

```bash
docker compose down -v
```
