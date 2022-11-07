# Speed Up Backend Development With WireMock And Docker

The code base for [Speed Up Backend Development With WireMock And Docker](https://medium.com/@georgeberar.contact/springboot-standardized-api-exception-handling-f31510861350) article.

## Getting Started

### Start
Start the mocked TODO service Docker compose command ``docker-compose up``.

> **8383** is used as running port

### Test
To test the mocked API you can do requests to one of the following endpoints:
- ``GET http://localhost:8383/api/v1/todos`` for getting TODOs
- ``GET http://localhost:8383/api/v1/todos/d8b25784-c16f-449a-9006-6972e8a9111b`` for getting TODO by Id