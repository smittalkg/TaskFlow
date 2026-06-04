# TaskFlow API

A multi-user task management REST API built with Spring Boot. Covers JWT authentication, validation, service layer patterns, and database migrations.

## Tech Stack

- **Java 21** + Spring Boot 4
- **Spring Security** + JWT (jjwt 0.12.6)
- **Spring Data JPA** + H2 (dev) / PostgreSQL (prod)
- **Flyway** for database migrations
- **MapStruct** for DTO mapping
- **Lombok** to reduce boilerplate
- **JUnit 5** + Mockito for testing

## Project Structure

```
src/main/java/com/example/TaskFlow/
├── controller/       # REST endpoints
├── service/          # Business logic
├── repository/       # JPA repositories
├── model/            # JPA entities
├── dto/              # Request/Response DTOs
├── mapper/           # MapStruct mappers
├── security/         # JWT filter, config, user details
└── exception/        # Global exception handler
```

## Getting Started

### Prerequisites
- Java 21
- Maven

### Run locally

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080` with the `dev` profile — H2 in-memory database, SQL logging enabled.

## API Endpoints

### Auth
| Method | Path | Description | Auth |
|--------|------|-------------|------|
| POST | `/auth/register` | Register a new user | Public |
| POST | `/auth/login` | Login, returns JWT token | Public |

### Tasks
| Method | Path | Description | Auth |
|--------|------|-------------|------|
| POST | `/tasks` | Create a task | Required |

## Usage

**Register:**
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name": "Sanchit", "email": "sanchit@test.com", "password": "secret123"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "sanchit@test.com", "password": "secret123"}'
```

**Create a task** (use token from login):
```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"name": "Buy groceries", "userId": 1, "taskPriority": "HIGH", "dateTime": "2027-01-01T10:00:00"}'
```

## Configuration

| Profile | Database | DDL | SQL Logging |
|---------|----------|-----|-------------|
| `dev` | H2 in-memory | validate | enabled |
| `prod` | PostgreSQL (via env vars) | validate | disabled |

### Environment variables (prod)

```bash
JWT_SECRET=your-secret-key
DB_URL=jdbc:postgresql://host:5432/taskflow
DB_USERNAME=user
DB_PASSWORD=password
```

## Running Tests

```bash
mvn test
```

Tests cover `TaskService` and `AuthService` with Mockito mocks — no Spring context or database required.

## Database Migrations

Managed by Flyway. Migration scripts are in `src/main/resources/db/migration/`.

| Version | Description |
|---------|-------------|
| V1 | Create users and task tables |
