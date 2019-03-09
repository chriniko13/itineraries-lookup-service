### Itineraries Lookup Service - Adidas Backend Assignment


```
.___  __  .__                                 .__                .____                  __
|   |/  |_|__| ____   ________________ _______|__| ____   ______ |    |    ____   ____ |  | ____ ________
|   \   __\  |/    \_/ __ \_  __ \__  \\_  __ \  |/ __ \ /  ___/ |    |   /  _ \ /  _ \|  |/ /  |  \____ \
|   ||  | |  |   |  \  ___/|  | \// __ \|  | \/  \  ___/ \___ \  |    |__(  <_> |  <_> )    <|  |  /  |_> >
|___||__| |__|___|  /\___  >__|  (____  /__|  |__|\___  >____  > |_______ \____/ \____/|__|_ \____/|   __/
                  \/     \/           \/              \/     \/          \/                 \/     |__|
                     _________                  .__
                    /   _____/ ______________  _|__| ____  ____
                    \_____  \_/ __ \_  __ \  \/ /  |/ ___\/ __ \
                    /        \  ___/|  | \/\   /|  \  \__\  ___/
                   /_______  /\___  >__|    \_/ |__|\___  >___  >
                           \/     \/                    \/    \/
                 _________ .__          .__       .__ __
                 \_   ___ \|  |_________|__| ____ |__|  | ______
                 /    \  \/|  |  \_  __ \  |/    \|  |  |/ /  _ \
                 \     \___|   Y  \  | \/  |   |  \  |    <  <_> )
                  \______  /___|  /__|  |__|___|  /__|__|_ \____/
                         \/     \/              \/        \/     
```

##### Assignee: Nikolaos Christidis (nick.christidis@yahoo.com)


#### Prequisities in order to local run
* Need to have up and running the [routes-service](https://github.com/chriniko13/routes-service)

#### How to run service (not dockerized)
* Two options:
    * Execute: 
        * `mvn clean install -DskipTests=true`
        * `java -jar -Dspring.profiles.active=dev -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector target/itineraries-lookup-service-1.0.0-SNAPSHOT.jar`
                
    * Execute:
        * `mvn spring-boot:run -Dspring.profiles.active=dev -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector`


#### How to run service (dockerized)
* Execute: `docker build -t itineraries-lookup-service .` in order to build docker image.

* Execute: `docker run -p 8181:8181 itineraries-lookup-service:latest` in order to run the container.


#### Execute Unit Tests
* Execute: `mvn clean test`


#### Execute Integration Tests (you should run docker-compose up first)
* Execute: `mvn clean integration-test -DskipUTs=true` or `mvn clean verify -DskipUTs=true`


#### Test Coverage (via JaCoCo)
* In order to generate reports execute: `mvn clean verify`
    * In order to see unit test coverage open with browser: `target/site/jacoco-ut/index.html`
    * In order to see integration test coverage open with browser: `target/site/jacoco-it/index.html`


#### Swagger (Documentation)
* First run the service.

* Then:
    * For UI visit: `http://localhost:8181/swagger-ui.html`

    * For plain JSON visit: `http://localhost:8181/v2/api-docs`


#### Example Request
TODO


#### Useful Docker Commands

* `docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <container_id>`