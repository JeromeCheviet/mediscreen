# Eureka-server

Eureka server is a service discovery which maintain a list of instances that are available for work within a microservice domain.

It is use by Mediscreen.

__Eureka server must be started before all microservices__

## Installation

This server run with __Java OpenJDK 17__ and __Maven 3.9.3__

You can build this application with the command:

```shell
mvn clean install
```
## How to use

You can run Eureka on specific server or in a docker

```yaml
version: '3.8'

services:
  eureka-server:
    build:
      context: eureka-server/.
      dockerfile: Dockerfile
    container_name: eureka-server
    ports:
      - "8101:8101"
```

