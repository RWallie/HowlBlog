# Howl Take Home

## Summary
---
Java server implementing CRUD operations for a blog post that contains a **title** and **message**. Utilizes Spring Boot, JPA, Hibernate, and H2 in memory database to transfer and store data between requests and responses. Provides Unit testing for the Service and Repository layers as well as Integration testing for the Controller.

## How To Run Server
---
Running on Java version 17 

In root directory run:
```
./mvnw spring-boot:run
```

Server listening on port 8080:

```
http://localhost:8080
```

You can access database at:

```
http://localhost:8080/h2
```

And Execute SQL queries 
as per `spring.h2.console.enabled=true`

### Login Credentials for H2 Console in Web Browser:
---

- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: 

Database is H2. Reason being it is in memory, meaning upon application restart the database will start fresh.

## Request Endpoints
---
- `GET` - `/blogs`       - gets all blogs from the `BLOGS` table

- `GET` - `/blogs/{id}`  - gets a blog by `id` from `BLOGS` table 

- `POST` - `/blogs`      - adds a blog to the `BLOGS` table - send data in Body as raw JSON

- `PUT` - `/blogs/{id}`  - updates a blog by `id` in the `BLOGS` table  - updated information in Body as raw JSON. Will only update non null fields.

- `DELETE` - `/blogs/{id}`       - deletes a blog by `id` from the `BLOGS` table

All requests that look for blogs by **id** throw `NotFound` exceptions if `id` is invalid

Java natively handles internal server errors and will respond with `500` status.

## Tests
---

Tests are found in `src/test/java/com/howl/blog` directory

- **Repository Tests**: Unit tests the `BlogRepository` for basic CRUD functionality from CrudRepository which JpaRepository extends. Validating that the Hibernate ORM works properly with the `BLOGS` entity.

- **Service Tests**: Unit tests the BlogServiceImplementation class methods, verifying they return the correct data. Also tests that `BlogNotFound` exception is thrown when invalid `id`s are used.

- **Controlelr Tests**: Integration tests for the `BlogController`. Utilizes MockMvc and Mockito to mock requests to our controller endpoints without having to start up the server. Is more cost/time efficient. Validates the appropriate data will be sent back as a response. Also checks for `404` `NotFound` status when invalid `id`s are sent to the controller.