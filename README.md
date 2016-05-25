The project needs java 8 and maven installed.

To build the project: (all commands are run in the project folder)
mvn clean install
The project can be with spring boot. just run Application.java


To call the service:
curl http://localhost:8080/primes/10 -H "Accept:application/json"
curl http://localhost:8080/primes/10 -H "Accept:application/xml"


The code is quite simple - it's only using Spring MVC and Java8.
- the main package 'primeservice' contains the controller and a response bean with annotations for json and xml responses.
- the 'service' package with the prime service.
- a unit test 'PrimeServiceImplTest' is testing the prime service and an integration test the controller.
- a integration test 'PrimeServiceControllerTestIT' is integration test for the controller.
