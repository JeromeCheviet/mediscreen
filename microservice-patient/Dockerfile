FROM amazoncorretto:17-al2-generic-jdk
WORKDIR /
COPY target/microservice-patient.jar microservice-patient.jar
COPY docker/application.properties application.properties
EXPOSE 8081
CMD java -jar microservice-patient.jar --spring.config.location=file:///application.properties
