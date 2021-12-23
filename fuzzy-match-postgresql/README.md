# Fuzzy Match With Postgres

The code base for [SpringBoot: Fuzzy Match With Postgres](https://medium.com/@georgeberar.contact/springboot-fuzzy-match-with-postgres-8eb6bfd17b58) article.

## Getting Started

### Start
Start the application using your favorite dev tool (IntelliJ or Eclipse) or with Maven command ``mvn spring-boot:run``.

> **8080** is used as running port

### Test
To test the API you can do requests to one of the following:
- ``GET http://localhost:8080/api/v1/todos`` for getting TODOs
- ``GET http://localhost:8080/api/v1/todos?partial_title=Go`` for getting TODOs which match a partial given title

For example getting all TODOs which are similar to `Clean`:  

**Request**  
``GET http://localhost:8080/api/v1/todos?partial_title=Clean``

**Response**  
```
[
    {
        "extRef": "c831fc27-3690-40fc-84a0-4a8b9df76afc",
        "title": "Clean House",
        "created": "2021-12-16T11:49:13.72173",
        "updated": "2021-12-16T11:49:13.72173"
    },
    {
        "extRef": "398f74ed-ff30-4f5a-95db-e904f3de1963",
        "title": "Klean Car",
        "created": "2021-12-16T11:49:13.739515",
        "updated": "2021-12-16T11:49:13.739515"
    }
]
```

> Note: the **extRef** changes every single time you start the app and leave the `db.flyway.clean` property with value **true**