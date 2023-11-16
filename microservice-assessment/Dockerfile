FROM amazoncorretto:17-al2-generic-jdk
WORKDIR /
COPY target/microservice-assessment.jar microservice-assessment.jar
COPY docker/application.properties application.properties
EXPOSE 8083
CMD java -jar microservice-assessment.jar --spring.config.location=file:///application.properties