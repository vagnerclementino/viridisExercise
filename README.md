# Viridis Exercise
  Hello! Thank you for your interest and effort in our hiring process. This stage seeks to evaluate your resourcefulness, creativity, and whim. See instructions below.

---

### Description
Conduct a proof of concept development of a fictitious application. This application is a simplified scope of industrial asset management, where we can manage equipment and maintenance orders.

To do this you will count with a basic back-end App with some methods already implemented. This App was developed based on Spring Boot, PostgreSQL 9.4, JDK 8, Maven, Lombok (tip: it has to be installed in the IDE), Swagger, among others.

### Goals

1. Implement "Create", "Update", and "Delete" for each entity.
1. Implement unit tests.
1. Allow requests only with authentication.
1. Implement cache on services.
1. Implement auditing on entity classes (tip: use Hibernate Envers).
1. Implement a React front-end that performs CRUD ops for each entity.

### How to Deliver

The produced code should be public on Github and you should send us the link to access. You should also provide an API documentation (with examples) as well as a README.md file with instructions to execute your app locally.

### What we're looking for

The quality of the code produced in terms of organization, documentation, and readability. Your skills of finding information on your own, solving problems, and communicating well your solution also counts!

**Good job!**

## Solution

For to execute this project you need *Docker* and *Docker Compose*. After this, 
you must run the following command:

```bash
 $ cd Exercise
 $ make run
```

For run tests you must execute:

```bash
 $ make test
```

For more commands,  execute:

```bash
 $ make help
```

## Get JWT Token

For tests propuses you could get a token running this command:

```bash
curl -X POST \
  http://localhost:8080/api/auth/signin \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
    	"username": "exercise",
    	"password": "viridis"
    }'
```

You should receive the following response:

```json
{"username":"exercise","token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVyY2lzZSIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNTcxMTkxODQzLCJleHAiOjE1NzEyNzgyNDN9.RCLMlMT_zqtA3J820GrJcwOZ2NwcgVS2ytBsGiGYXXo"
}
````

