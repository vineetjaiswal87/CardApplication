# Card Application

The Card application is a rest based service that serves the purpose of adding any new card and then fetching the same card details back.

## Tech Stack

* Java 8
* Spring Boot 
* H2 Database
* Spring Rest Documentations

## Documentation

* Spring Rest doc has been used for documentation and same can be generated by using maven install command which         will place the document under target/generated-docs folder.
* For easier access it is placed in /src/main/resources/documentation/documentation.html location.

## Database

* H2 In-memory database has been used for the application.
* Login URL once application is started http://localhost:8080/h2
* Credentials are as below:-
	JDBC Url - jdbc:h2:mem:carddb
	Username - causer
	Password - capass

## Assumptions

* Once a card has been added user should not be allowed to add it again.
* Assuming request will have userid hence saving the same in DB.
* Assuming functionality could be expanded in future hence have added modifiedUserId and modifiedDateTime.

## Run application

* Please download the code from GITHUB using HTTPS or SSH as per your reference using GIT clone.
* Import it as an maven project and install Lombok in your IDE.
* Build and run it as an SpringBoot application.
* Refer the sample request and response from documentation.html.

## Service URL's

* http://localhost:8080/card/v1/add
* http://localhost:8080/card/v1/getAll	

## Testing

* Card Application tests are split into Junit and JPA test cases to test the DB insert and fetch.

