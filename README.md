# Datask

## Run with docker

You can run this application with docker. For this you should execute the next commands

1. mvn clean package
2. docker build -t datask:0 .
3. docker run -p 8080:8080 -t datask:0

To stop the container, you should search the name with:

* docker ps

With the name, you can stop the container with:

* docker stop name

## Swagger

You can see the endpoints in the url `${server.url}/swagger-ui.html`. With the docker in local `http://localhost:8080/swagger-ui.html`.
