FROM docker.io/library/openjdk:17
ARG JAR_FILE=build/libs/*.jar
ARG RESOURCE_PATH=build/resources/main/
ARG APPLICATION_FILE=${RESOURCE_PATH}/application.yml
COPY ${JAR_FILE} app.jar
COPY ${APPLICATION_FILE} config/application.yml
ENTRYPOINT ["java","-jar","/app.jar"]
