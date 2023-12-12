# Microservice-notes

Microservice-notes is a microservice which manage all the patient history.

It is use by Mediscreen.

It's a REST API application.

## Installation

This microservice was developed with __Java OpenJDK 17__ and __Maven 3.9.3__

You can build this application with the command:

```shell
mvn clean install
```

To skip tests, you can add the option `-DskipTests`

> __Before build, please be sure to use the right database connection information from properties
file `./src/main/ressoures/application.properties`.__

After building the app, you can use the `Dockerfile` script present in the project.

To run the microservice, ensure you to have a __MongoDB 7.0.0__ minimal installed on your server or in docker image.

```yaml
version: '3.8'

services:
  mongodb:
    image: mongodb/mongodb-community-server:7.0.0-ubi8
    container_name: mongodb_notes
    volumes:
      - mongodb_mediscreen:/data/db
    ports:
      - '27017:27017'
volumes:
  mongodb_mediscreen:
```

> __You must create database mediscreen_note, but you don't need to create table, Microservice-notes do it for you.__

# Endpoints

For more details you can consult the swagger page at `http://localhost:8082/swagger-ui/index.html`

> GET - /patHistory/<patId> - Get all notes for a specific patient

> GET - /patHistory/note/<noteId> - Get a specific note

> POST - /patHistory/add - Create a new note. The body must contains all needed information

> PUT - /patHistory - Update a note. The body must contains all needed information

> DELETE - /patHistory/<noteId> - Delete a specific note.
