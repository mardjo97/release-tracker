# Release Tracker

## Overview

This project is a simple release tracker application. It exposes few APIs to create, read, update,
and delete releases.

## Technologies

- Java 21
- Spring Boot
- Hibernate
- Liquibase
- H2 Database
- springdoc-openapi
- JUnit and Mockito
- Docker
- Maven

## Getting started

### Requirements

- Java 21
- Maven 3.6+
- Docker (optional)

---

### Running the application locally

1. Build the project:

   ```bash
   mvn clean install 
   ```

2. Run the application:

   ```bash
   mvn spring-boot:run
   ```

The app will start and will be accessible on port 8080.

3. Access the Swagger UI:
   `http://localhost:8080/swagger-ui.html`

### Running the application on docker

1. Make sure Docker is installed.

2. In the root directory execute the command:

    ```bash
    docker-compose up --build
    ```
   This will build and start the app container along with database.

   Access the app and Swagger UI same as when running app locally.

### Application configuration

In this project, there are 3 property files to support different scenarios:

- `application.properties` - The default properties for all environments - default for development.
- `application-test.properties` - Overrides properties for the test environment - used for integration tests.
- `application-prod.properties` - Overrides properties for the production environment - used in docker-compose.

### Liquibase

- Liquibase is used to manage the schema updates.
- ChangeSets are located in src/main/resources/db/ directory
- On any app start, Liquibase will automatically execute new changeSets.

### Testing

- Unit and integration tests are written
- Integration tests are run locally in the H2 database

1. Run tests with:

    ```bash
    mvn verify
    ```

### API documentation

- Swagger-ui URL: http://localhost:8080/swagger-ui.html
- OpenAPI spec URL: http://localhost:8080/v3/api-docs

### Notes

- Soft delete implemented by a boolean enabled flag; deleted entities are not physically removed. When retrieving
  releases this boolean flag needs to be checked.
- PATCH API updates only specified fields, some like createdAt, lastUpdateAt are ignored.
- Validation of the DTOs are implemented.
- In the root of the project, the postman collection, **Release tracker.postman_collection.json**, can be found, with
  example requests.