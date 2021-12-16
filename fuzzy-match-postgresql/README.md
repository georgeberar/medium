# Standardized API Exception Handling

The code base for [SpringBoot: Standardized API Exception Handling]("") article.

## Getting Started

### Start
Start the application using your favorite dev tool (IntelliJ or Eclipse) or with Maven command ``mvn spring-boot:run``.

> **8080** is used as running port

### Test
To test the API you can do requests to one of the following endpoints:
- ``GET http://localhost:8080/api/v1/todos`` for getting TODOs
- ``GET http://localhost:8080/api/v1/todos/{extRef}`` for getting TODO by external reference
- ``POST http://localhost:8080/api/v1/todos`` with JSON body ``{ "title": "Test" }`` for creating TODO
- ``DELETE http://localhost:8080/api/v1/todos/{extRef}`` for deleting TODO by external reference
