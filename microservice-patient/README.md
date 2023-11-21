# Microservice-patient

Microservice-patient is a microservice which manage all general information from patient.

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

To run the microservice, ensure you to have a __MariaDB 11.0.2__ minimal installed on your server or in docker image.

```yaml
version: '3.8'

services:
  db_patient:
    image: mariadb:11.0.2
    container_name: db_patient
    environment:
      MYSQL_ROOT_PASSWORD: rootroot
      MYSQL_DATABASE: mediscreen_patient
      MYSQL_USER: mediscreen
      MYSQL_PASSWORD: M3d1Scr33n
    ports:
      - "3306:3306"
    volumes:
      - dbpatient_mediscreen:/var/lib/mysql

volumes:
  dbpatient_mediscreen:
```

> __You don't need to create table, Microservice-patient do it for you.__

# Endpoints

For more details you can consult the swagger page at `http://localhost:8081/swagger-ui/index.html`

> GET - /patient - Get all patients

> GET - /patient/<patientId> - Get information for a specific patient

> POST - /patient/add - Create a new patient. The body must contains all needed information

> PUT - /patient/<patientId> - Update a patient. The body must contains all needed information

> DELETE - /patient/<patientId> - Delete a patient.
