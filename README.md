# Caching with Redis Example

A Spring Boot application that demonstrates Redis caching with a simple Employee CRUD API backed by an in-memory H2 database.

## Features

- REST API for employee operations
- Spring Data JPA with H2 database
- Redis cache integration using Spring Cache
- Example cache annotations for read/write invalidation
- Basic unit and controller tests

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Spring Data Redis
- Spring Cache
- H2 Database
- Maven

## Project Structure

```text
src/
  main/
    java/
      com/tthahir/
        controller/
        dto/
        entity/
        repository/
        service/
        utils/
        CachingWithRedisExampleApplication.java
    resources/
      application.yml
  test/
    java/
      com/tthahir/
        controller/
        service/
```

## Prerequisites

- Java 17+
- Maven
- Redis installed and running locally on port 6379

## Run Redis Locally

If Redis is installed locally, start it with:

```bash
redis-server
```

## Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## API Endpoints

### Home

```http
GET /home
```

### Get all employees

```http
GET /getAllEmployees
```

### Get employee by ID

```http
GET /getAllEmployees/{id}
```

Example:

```http
GET /getAllEmployees/1
```

## H2 Database Console

The application enables the H2 console, which can be accessed at:

```text
http://localhost:8080/h2-console
```

Use the JDBC URL:

```text
jdbc:h2:mem:testdb
```

Username:

```text
sa
```

Password:

```text
(empty)
```

## Cache Behavior

The service layer uses Spring cache annotations:

- `@Cacheable` to fetch and store values in Redis
- `@CacheEvict` to remove stale cache entries after writes
- `@CachePut` to update cached objects after modification

This helps reduce repeated database calls for frequently used employee data.

## Testing

Run the tests with:

```bash
mvn test
```

## Notes

- The app uses `ddl-auto: create-drop`, which is suitable for demo/dev usage.
- For production, you would typically switch this to a safer configuration.

## License

This project is for educational/demo purposes.
