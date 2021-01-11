CurrencyConverterApp is a **REST API** that lets you get exchange rates for many currencies.

It was created as a learning experience for Spring and Hibernate. It's a backend app that should be 'consumed' by a frontend REST client eg. Angular JS.
It supports basic CRUD operations via HTTP requests.
Stack used:
* **Spring Boot** 
* **Flyway** for control version of database 
* **Hibernate** for ORM
* **PostgreSQL** to store exchange rates
* **Flyway** to make request to external api
* **JUnit & Mockito** for unit and integration testing

Application is deployed at Heroku, to recieve exchange rate, user should call:
* `http://slepy-currency-converter.herokuapp.com/api/currencies?from={currency}&to={currency}`

FROM and TO variables can be assign by the currencies which application should convert. 
Application will return the lastest exchange rate, also if you want to know exchange rate from concrete day.

You can make call:
* `http://slepy-currency-converter.herokuapp.com/api/currencies?from={currency}&to={currency}&createdAt={date}`

where createdAt is date variable of type yyyy-mm-dd

