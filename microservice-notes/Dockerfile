FROM amazoncorretto:17-al2-generic-jdk
WORKDIR /
COPY target/microservice-notes.jar microservice-notes.jar
COPY docker/application.properties application.properties
EXPOSE 8082
CMD java -jar microservice-notes.jar --spring.config.location=file:///application.properties
