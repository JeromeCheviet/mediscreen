# Microservice-assessment

Microservice-assessment is a microservice which assess the patient diabetes risk

It is use by Mediscreen.

It's a REST API application.

> __This API is dependent on microservice-patients and microservice-notes.__

## Installation

This microservice was developed with __Java OpenJDK 17__ and __Maven 3.9.3__

You can build this application with the command:

```shell
mvn clean install
```

To skip tests, you can add the option `-DskipTests`

# Endpoints

For more details you can consult the swagger page at `http://localhost:8083/swagger-ui/index.html`

> GET - /assess/<id> - get the assessment for a specific patient
