FROM openjdk:8-jdk-alpine
#FROM maven:alpine


WORKDIR /app

COPY . /app

#RUN mvn -v
#RUN mvn clean install -DskipTests

EXPOSE 8181

LABEL maintainer='nick.christidis@yahoo.com'

ADD ./target/itineraries-lookup-service-1.0.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dspring.profiles.active=container", "-jar", "app.jar", "container-entrypoint"]
