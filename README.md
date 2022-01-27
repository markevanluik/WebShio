# WebShio

Simple dummy webshop. (Work in progress)

## Description

Techstack: [PostgreSQL](https://www.postgresql.org/), [Spring Boot](http://projects.spring.io/spring-boot/), [Angular](https://angular.io/)

Features: Login(Spring security/JWT), Payment(EveryPayAPI), API documentation(Swagger), Caching(Google Guava), 

## Running the application locally

For Spring boot setup refer to their [repository](https://github.com/spring-projects/spring-boot).

To run a Spring Boot application on your local machine execute the `main` method in the `ee/mark/webshiospring/WebShioSpringApplication.java ` class from your IDE.

Add properties file `src/main/resources/application.properties`

```
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/webshop
spring.datasource.username=[yourDBUsernameHere]
spring.datasource.password=[yourDBPasswordHere]

everypay.url=https://igw-demo.every-pay.com/api/v4/payments/oneoff
everypay.username=[yourEveryPayUsernameHere]
everypay.accountname=[yourEveryPayAccountNameHere]
everypay.authorization=[yourBasicAuthTokenHere]
everypay.customerurl=https://www.example.com

jwt.signingkey=[yourSigningKeyHere]
```
For Angular setup refer to their [repository](https://github.com/angular/angular/blob/master/README.md).

After setup if you are using VS Code you can start the web server and open the application in the browser with your terminal like so: 
```bash
$ ng s -o
```
To see API documentation make sure the back end server is running and visit [http://localhost:8080/swagger-ui.html](http://localhost:[port-in-use]/swagger-ui.html)
