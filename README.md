# WebShio

Simple dummy webshop. (Work in progress)

## Description

Techstack: [PostgreSQL](https://www.postgresql.org/), [Spring Boot](http://projects.spring.io/spring-boot/), [Angular](https://angular.io/)

Features: Login(Spring security/JWT), Payment(EveryPayAPI), API documentation(Swagger), Caching(Google Guava), 

## Running the application locally

For Spring boot setup refer to their [repository](https://github.com/spring-projects/spring-boot).

To run a Spring Boot application on your local machine execute the `main` method in the `ee/mark/webshiospring/WebShioSpringApplication.java ` class from your IDE.

Configure the database by modifiying `src/main/resources/application.properties`

For Angular setup refer to their [repository](https://github.com/angular/angular/blob/master/README.md).

After setup if you are using VS Code you can start the web server with you terminal like so: 
```bash
$ ng s -o
```
