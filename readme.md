# Kitchensink Spring Boot Application

This project is a Spring Boot application that demonstrates various features including member registration, REST API, and Thymeleaf templating.

## Features

- Member registration with form validation
- REST API for member management
- Thymeleaf templates for server-side rendering
- H2 in-memory database for data persistence

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Spring Boot 3.1.5 or higher

## Clone repo and edit source code

- Checkout code from it's root folder
- I've used IntelliJ CE, and run directly as a Spring Boot App

## Getting Started


1. Navigate to the project directory:
   ```
   cd kitchensink-spring-boot
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

4. Open a web browser and go to `http://localhost:8080`

## Project Structure

- `src/main/java/com/example/kitchensink`: Java source files
  - `controller`: MVC controllers
  - `model`: Domain models
  - `service`: Business logic services
  - `data`: Data access layer
  - `rest`: REST API controllers
- `src/main/resources`: Configuration files and static resources
  - `templates`: Thymeleaf HTML templates
  - `static`: CSS and other static files

## REST Endpoints

To manually test all the endpoints exposed in your application, you can use tools like cURL, Postman, or even your web browser for GET requests. Based on the provided code, here are examples:

List all members (GET request):

curl http://localhost:8080/kitchensink/members

Register a new member (POST request):

curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","email":"john@example.com","phoneNumber":"1234567890"}' http://localhost:8080/kitchensink/members

Get a specific member by ID (GET request):

curl http://localhost:8080/kitchensink/members/1
