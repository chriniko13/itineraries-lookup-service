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


#### Example Request - Response
* Post at:
  with body:
  
  ```json
    {
    	"name":"Huelva",
    	"country":"Spain"
    }
  ```
  
  Response:
  
  ```json
      {
            "itinerariesInfoByProcessingCriteria": {
                "less-connections-and-less-time" : [...],
                "less-connections" : [...],
                "less-time" : [...]
            }
      }

  ```
  
  
  ```json
      {
          "itinerariesInfoByProcessingCriteria": {
              "less-connections-and-less-time": [
                  {
                      "fastDisplay": "[Huelva ---> Salamanca]",
                      "noOfConnections": 1,
                      "noOfVisitedCities": 2,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T04:27:28Z",
                      "timeDurationOfItinerary": "PT4H",
                      "detailedRouteInfo": [
                          {
                              "id": "e065726d-6a76-42c1-b56e-bdb0b456ea80",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Salamanca",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T04:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Vitoria ---> Santander ---> Logrono]",
                      "noOfConnections": 3,
                      "noOfVisitedCities": 4,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T05:27:28Z",
                      "timeDurationOfItinerary": "PT5H",
                      "detailedRouteInfo": [
                          {
                              "id": "1ced18c3-07bc-4c13-930a-3c92130c26b7",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Vitoria",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T02:27:28Z"
                          },
                          {
                              "id": "a654dca7-7471-45e7-9803-cab21c6ce09b",
                              "city": {
                                  "name": "Vitoria",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Santander",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T02:27:28Z",
                              "arrivalTime": "2019-03-11T04:27:28Z"
                          },
                          {
                              "id": "fb0fd88e-0cd8-491a-8359-5627cf59762c",
                              "city": {
                                  "name": "Santander",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Logrono",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T04:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Gijon ---> Linares]",
                      "noOfConnections": 2,
                      "noOfVisitedCities": 3,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "90dcf7e4-8643-4e13-9aaa-d136ae1157b2",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Gijon",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "23063095-16b4-44ef-94fe-31345ef2dbdc",
                              "city": {
                                  "name": "Gijon",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Linares",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Arrecife ---> Las Palmas ---> Toledo]",
                      "noOfConnections": 3,
                      "noOfVisitedCities": 4,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "14631eda-4bfd-4e3a-97ea-04fc15e07a31",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Arrecife",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "c154d3d6-228d-4a4c-b89a-7974e8213c43",
                              "city": {
                                  "name": "Arrecife",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Las Palmas",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "6d9cca1f-c205-478c-ad9e-57a3fced6583",
                              "city": {
                                  "name": "Las Palmas",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Toledo",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> La Coruna ---> Ourense ---> Bilbao ---> Santa Cruz de Tenerife]",
                      "noOfConnections": 4,
                      "noOfVisitedCities": 5,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "9a53968c-8ad2-4199-b979-3cc748a203cc",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "La Coruna",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "ff6bc2b5-8a6b-416a-b1ce-a548753f67d8",
                              "city": {
                                  "name": "La Coruna",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Ourense",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T02:27:28Z"
                          },
                          {
                              "id": "bdd8fd41-cee3-4246-bbe1-b784339dc359",
                              "city": {
                                  "name": "Ourense",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Bilbao",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T02:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          },
                          {
                              "id": "af56a61c-302a-4472-9e29-400c33526b6b",
                              "city": {
                                  "name": "Bilbao",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Santa Cruz de Tenerife",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T05:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Jaen ---> Malaga ---> Albacete ---> Guadalajara]",
                      "noOfConnections": 4,
                      "noOfVisitedCities": 5,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T08:27:28Z",
                      "timeDurationOfItinerary": "PT8H",
                      "detailedRouteInfo": [
                          {
                              "id": "6ae8d992-a9eb-4765-8fcb-f93be82eb93f",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Jaen",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "855121a6-2797-4f27-9a38-2f6fa58f0d5e",
                              "city": {
                                  "name": "Jaen",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Malaga",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "574e1a5d-2e0c-48c8-9bfd-0810576b2255",
                              "city": {
                                  "name": "Malaga",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Albacete",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          },
                          {
                              "id": "1a5f7da1-bc99-4b37-a334-c9ae50403bcc",
                              "city": {
                                  "name": "Albacete",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Guadalajara",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T07:27:28Z",
                              "arrivalTime": "2019-03-11T08:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Merida ---> Algeciras ---> Marbella ---> Almeria ---> Cordoba]",
                      "noOfConnections": 5,
                      "noOfVisitedCities": 6,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T13:27:28Z",
                      "timeDurationOfItinerary": "PT13H",
                      "detailedRouteInfo": [
                          {
                              "id": "a48d84a1-e14c-4344-ae67-076646582407",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Merida",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "955cd782-9b28-4601-bc26-63093a127d2b",
                              "city": {
                                  "name": "Merida",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Algeciras",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          },
                          {
                              "id": "f4e84c26-7c86-45ae-93f8-ddb04c9191f0",
                              "city": {
                                  "name": "Algeciras",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Marbella",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T05:27:28Z",
                              "arrivalTime": "2019-03-11T09:27:28Z"
                          },
                          {
                              "id": "1318dc03-2d34-46af-a10f-b517bbf7929d",
                              "city": {
                                  "name": "Marbella",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Almeria",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T09:27:28Z",
                              "arrivalTime": "2019-03-11T11:27:28Z"
                          },
                          {
                              "id": "3d520eb0-7fe9-45c4-9ac4-0b10b30b8d27",
                              "city": {
                                  "name": "Almeria",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Cordoba",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T11:27:28Z",
                              "arrivalTime": "2019-03-11T13:27:28Z"
                          }
                      ]
                  }
              ],
              "less-connections": [
                  {
                      "fastDisplay": "[Huelva ---> Salamanca]",
                      "noOfConnections": 1,
                      "noOfVisitedCities": 2,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T04:27:28Z",
                      "timeDurationOfItinerary": "PT4H",
                      "detailedRouteInfo": [
                          {
                              "id": "e065726d-6a76-42c1-b56e-bdb0b456ea80",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Salamanca",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T04:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Gijon ---> Linares]",
                      "noOfConnections": 2,
                      "noOfVisitedCities": 3,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "90dcf7e4-8643-4e13-9aaa-d136ae1157b2",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Gijon",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "23063095-16b4-44ef-94fe-31345ef2dbdc",
                              "city": {
                                  "name": "Gijon",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Linares",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Arrecife ---> Las Palmas ---> Toledo]",
                      "noOfConnections": 3,
                      "noOfVisitedCities": 4,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "14631eda-4bfd-4e3a-97ea-04fc15e07a31",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Arrecife",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "c154d3d6-228d-4a4c-b89a-7974e8213c43",
                              "city": {
                                  "name": "Arrecife",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Las Palmas",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "6d9cca1f-c205-478c-ad9e-57a3fced6583",
                              "city": {
                                  "name": "Las Palmas",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Toledo",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Vitoria ---> Santander ---> Logrono]",
                      "noOfConnections": 3,
                      "noOfVisitedCities": 4,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T05:27:28Z",
                      "timeDurationOfItinerary": "PT5H",
                      "detailedRouteInfo": [
                          {
                              "id": "1ced18c3-07bc-4c13-930a-3c92130c26b7",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Vitoria",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T02:27:28Z"
                          },
                          {
                              "id": "a654dca7-7471-45e7-9803-cab21c6ce09b",
                              "city": {
                                  "name": "Vitoria",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Santander",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T02:27:28Z",
                              "arrivalTime": "2019-03-11T04:27:28Z"
                          },
                          {
                              "id": "fb0fd88e-0cd8-491a-8359-5627cf59762c",
                              "city": {
                                  "name": "Santander",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Logrono",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T04:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Jaen ---> Malaga ---> Albacete ---> Guadalajara]",
                      "noOfConnections": 4,
                      "noOfVisitedCities": 5,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T08:27:28Z",
                      "timeDurationOfItinerary": "PT8H",
                      "detailedRouteInfo": [
                          {
                              "id": "6ae8d992-a9eb-4765-8fcb-f93be82eb93f",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Jaen",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "855121a6-2797-4f27-9a38-2f6fa58f0d5e",
                              "city": {
                                  "name": "Jaen",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Malaga",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "574e1a5d-2e0c-48c8-9bfd-0810576b2255",
                              "city": {
                                  "name": "Malaga",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Albacete",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          },
                          {
                              "id": "1a5f7da1-bc99-4b37-a334-c9ae50403bcc",
                              "city": {
                                  "name": "Albacete",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Guadalajara",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T07:27:28Z",
                              "arrivalTime": "2019-03-11T08:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> La Coruna ---> Ourense ---> Bilbao ---> Santa Cruz de Tenerife]",
                      "noOfConnections": 4,
                      "noOfVisitedCities": 5,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "9a53968c-8ad2-4199-b979-3cc748a203cc",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "La Coruna",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "ff6bc2b5-8a6b-416a-b1ce-a548753f67d8",
                              "city": {
                                  "name": "La Coruna",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Ourense",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T02:27:28Z"
                          },
                          {
                              "id": "bdd8fd41-cee3-4246-bbe1-b784339dc359",
                              "city": {
                                  "name": "Ourense",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Bilbao",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T02:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          },
                          {
                              "id": "af56a61c-302a-4472-9e29-400c33526b6b",
                              "city": {
                                  "name": "Bilbao",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Santa Cruz de Tenerife",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T05:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Merida ---> Algeciras ---> Marbella ---> Almeria ---> Cordoba]",
                      "noOfConnections": 5,
                      "noOfVisitedCities": 6,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T13:27:28Z",
                      "timeDurationOfItinerary": "PT13H",
                      "detailedRouteInfo": [
                          {
                              "id": "a48d84a1-e14c-4344-ae67-076646582407",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Merida",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "955cd782-9b28-4601-bc26-63093a127d2b",
                              "city": {
                                  "name": "Merida",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Algeciras",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          },
                          {
                              "id": "f4e84c26-7c86-45ae-93f8-ddb04c9191f0",
                              "city": {
                                  "name": "Algeciras",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Marbella",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T05:27:28Z",
                              "arrivalTime": "2019-03-11T09:27:28Z"
                          },
                          {
                              "id": "1318dc03-2d34-46af-a10f-b517bbf7929d",
                              "city": {
                                  "name": "Marbella",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Almeria",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T09:27:28Z",
                              "arrivalTime": "2019-03-11T11:27:28Z"
                          },
                          {
                              "id": "3d520eb0-7fe9-45c4-9ac4-0b10b30b8d27",
                              "city": {
                                  "name": "Almeria",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Cordoba",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T11:27:28Z",
                              "arrivalTime": "2019-03-11T13:27:28Z"
                          }
                      ]
                  }
              ],
              "less-time": [
                  {
                      "fastDisplay": "[Huelva ---> Salamanca]",
                      "noOfConnections": 1,
                      "noOfVisitedCities": 2,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T04:27:28Z",
                      "timeDurationOfItinerary": "PT4H",
                      "detailedRouteInfo": [
                          {
                              "id": "e065726d-6a76-42c1-b56e-bdb0b456ea80",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Salamanca",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T04:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Vitoria ---> Santander ---> Logrono]",
                      "noOfConnections": 3,
                      "noOfVisitedCities": 4,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T05:27:28Z",
                      "timeDurationOfItinerary": "PT5H",
                      "detailedRouteInfo": [
                          {
                              "id": "1ced18c3-07bc-4c13-930a-3c92130c26b7",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Vitoria",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T02:27:28Z"
                          },
                          {
                              "id": "a654dca7-7471-45e7-9803-cab21c6ce09b",
                              "city": {
                                  "name": "Vitoria",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Santander",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T02:27:28Z",
                              "arrivalTime": "2019-03-11T04:27:28Z"
                          },
                          {
                              "id": "fb0fd88e-0cd8-491a-8359-5627cf59762c",
                              "city": {
                                  "name": "Santander",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Logrono",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T04:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Arrecife ---> Las Palmas ---> Toledo]",
                      "noOfConnections": 3,
                      "noOfVisitedCities": 4,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "14631eda-4bfd-4e3a-97ea-04fc15e07a31",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Arrecife",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "c154d3d6-228d-4a4c-b89a-7974e8213c43",
                              "city": {
                                  "name": "Arrecife",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Las Palmas",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "6d9cca1f-c205-478c-ad9e-57a3fced6583",
                              "city": {
                                  "name": "Las Palmas",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Toledo",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Gijon ---> Linares]",
                      "noOfConnections": 2,
                      "noOfVisitedCities": 3,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "90dcf7e4-8643-4e13-9aaa-d136ae1157b2",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Gijon",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "23063095-16b4-44ef-94fe-31345ef2dbdc",
                              "city": {
                                  "name": "Gijon",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Linares",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> La Coruna ---> Ourense ---> Bilbao ---> Santa Cruz de Tenerife]",
                      "noOfConnections": 4,
                      "noOfVisitedCities": 5,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T07:27:28Z",
                      "timeDurationOfItinerary": "PT7H",
                      "detailedRouteInfo": [
                          {
                              "id": "9a53968c-8ad2-4199-b979-3cc748a203cc",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "La Coruna",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "ff6bc2b5-8a6b-416a-b1ce-a548753f67d8",
                              "city": {
                                  "name": "La Coruna",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Ourense",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T02:27:28Z"
                          },
                          {
                              "id": "bdd8fd41-cee3-4246-bbe1-b784339dc359",
                              "city": {
                                  "name": "Ourense",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Bilbao",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T02:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          },
                          {
                              "id": "af56a61c-302a-4472-9e29-400c33526b6b",
                              "city": {
                                  "name": "Bilbao",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Santa Cruz de Tenerife",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T05:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Jaen ---> Malaga ---> Albacete ---> Guadalajara]",
                      "noOfConnections": 4,
                      "noOfVisitedCities": 5,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T08:27:28Z",
                      "timeDurationOfItinerary": "PT8H",
                      "detailedRouteInfo": [
                          {
                              "id": "6ae8d992-a9eb-4765-8fcb-f93be82eb93f",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Jaen",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "855121a6-2797-4f27-9a38-2f6fa58f0d5e",
                              "city": {
                                  "name": "Jaen",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Malaga",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T03:27:28Z"
                          },
                          {
                              "id": "574e1a5d-2e0c-48c8-9bfd-0810576b2255",
                              "city": {
                                  "name": "Malaga",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Albacete",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T03:27:28Z",
                              "arrivalTime": "2019-03-11T07:27:28Z"
                          },
                          {
                              "id": "1a5f7da1-bc99-4b37-a334-c9ae50403bcc",
                              "city": {
                                  "name": "Albacete",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Guadalajara",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T07:27:28Z",
                              "arrivalTime": "2019-03-11T08:27:28Z"
                          }
                      ]
                  },
                  {
                      "fastDisplay": "[Huelva ---> Merida ---> Algeciras ---> Marbella ---> Almeria ---> Cordoba]",
                      "noOfConnections": 5,
                      "noOfVisitedCities": 6,
                      "departureTimeOfItinerary": "2019-03-11T00:27:28Z",
                      "arrivalTimeOfItinerary": "2019-03-11T13:27:28Z",
                      "timeDurationOfItinerary": "PT13H",
                      "detailedRouteInfo": [
                          {
                              "id": "a48d84a1-e14c-4344-ae67-076646582407",
                              "city": {
                                  "name": "Huelva",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Merida",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T00:27:28Z",
                              "arrivalTime": "2019-03-11T01:27:28Z"
                          },
                          {
                              "id": "955cd782-9b28-4601-bc26-63093a127d2b",
                              "city": {
                                  "name": "Merida",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Algeciras",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T01:27:28Z",
                              "arrivalTime": "2019-03-11T05:27:28Z"
                          },
                          {
                              "id": "f4e84c26-7c86-45ae-93f8-ddb04c9191f0",
                              "city": {
                                  "name": "Algeciras",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Marbella",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T05:27:28Z",
                              "arrivalTime": "2019-03-11T09:27:28Z"
                          },
                          {
                              "id": "1318dc03-2d34-46af-a10f-b517bbf7929d",
                              "city": {
                                  "name": "Marbella",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Almeria",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T09:27:28Z",
                              "arrivalTime": "2019-03-11T11:27:28Z"
                          },
                          {
                              "id": "3d520eb0-7fe9-45c4-9ac4-0b10b30b8d27",
                              "city": {
                                  "name": "Almeria",
                                  "country": "Spain"
                              },
                              "destinyCity": {
                                  "name": "Cordoba",
                                  "country": "Spain"
                              },
                              "departureTime": "2019-03-11T11:27:28Z",
                              "arrivalTime": "2019-03-11T13:27:28Z"
                          }
                      ]
                  }
              ]
          }
      }
  ```


#### Useful Docker Commands

* `docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <container_id>`