# Tasks Organizer

Simple realization of (todo) tasks organizer and editor in Kotlin Spring Boot (with PostgreSQL)

## Features

### User Management

- Create, view, update, and delete users
- Get all users or specific user by ID

### Notes Management

- Create, view, update, and delete notes
- Search notes by author
- Full-text search in note titles and content
- Get specific note by ID

## Technology Stack

- **Kotlin** - primary programming language
- **Spring Boot 3.5.6** - application framework
- **PostgreSQL** - database
- **Spring Data JPA** - database operations
- **Docker & Docker Compose** - containerization and orchestration
- **Maven** - dependency management

## Prerequisites

- Java 17
- Docker and Docker Compose
- Maven 3.6+

## Installation & Running

### 1. Clone the repository

```bash
git clone https://github.com/UmbrellaLeaf5/tasks_organizer
cd tasks_organizer
```

### 2. Environment setup

Create a `.env` file in the project root:

```env
POSTGRES_DB=tasks_organizer
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
```

### 3. Start the database

```bash
chmod +x restart.sh
./restart.sh
```

The script will:

- Stop existing containers
- Start PostgreSQL in Docker
- Initialize the database schema

### 4. Run the application

```bash
mvn spring-boot:run
```

The application will be available at: `http://localhost:8080`

## API Endpoints

### Users

- `GET /api/users` - get all users
- `GET /api/users?id={user_id}` - get user by ID
- `POST /api/users` - create new user
- `PATCH /api/users?id={user_id}` - update user
- `DELETE /api/users?id={user_id}` - delete user

### Notes

- `GET /api/notes?author_id={author_id}` - get notes by author
- `GET /api/notes?search={query}&author_id={author_id}` - search notes
- `GET /api/notes?id={note_id}` - get note by ID
- `POST /api/notes?author_id={author_id}` - create note
- `PATCH /api/notes?id={note_id}` - update note
- `DELETE /api/notes?id={note_id}` - delete note

## Project Structure

```
src/main/kotlin/io/github/umbrellaleaf5/tasks_organizer/
├── controller/          # REST controllers
├── entity/              # JPA entities
├── repository/          # Spring Data repositories
├── service/             # Business logic
├── dto/                 # Data Transfer Objects
│   ├── request/         # Request DTOs
│   └── response/        # Response DTOs
├── exception/           # Custom exceptions
└── handler/             # Exception handlers
```

## Application Configuration

Main configuration is located in `application.yml`:

- Server port: 8080
- PostgreSQL connection via environment variables
- Logging and JPA settings
