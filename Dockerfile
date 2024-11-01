FROM docker.io/amazoncorretto:17.0.8-alpine3.17

WORKDIR /opt/app

COPY target/*.jar /opt/app/app.jar

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]