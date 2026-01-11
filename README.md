# 🍎 Fruit API - REST API with Spring Boot and H2

![Java](https://img.shields.io/badge/Java-21-orange?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen?style=flat&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?style=flat&logo=apachemaven)
![H2](https://img.shields.io/badge/H2-Database-blue?style=flat)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat&logo=docker)

REST API for managing a fruit shop inventory developed with **Spring Boot** and **H2 in-memory database**.  
The project follows **MVC architecture**, **clean code principles**, and **TDD (Test-Driven Development)**.

---

## 📋 Table of Contents

- [Description](#-description)
- [Technologies](#-technologies)
- [Architecture](#-architecture)
- [Endpoints](#-endpoints)
- [Installation](#-installation)
- [Execution](#-execution)
- [Tests](#-tests)
- [Docker](#-docker)
- [Project Structure](#-project-structure)
- [Validations](#-validations)
- [Error Handling](#-error-handling)
- [Configuration](#-configuration)

---

## 🎯 Description

This backend application allows **managing a fruit shop inventory**, storing for each fruit:

- **Name**
- **Weight in kilograms**

The API implements a **complete CRUD**:

- Create fruit
- Retrieve all fruits
- Retrieve fruit by ID
- Update fruit
- Delete fruit

Best practices applied:

- MVC layered architecture
- DTO separation (Request / Response)
- Bean Validation + custom validators
- Global exception handling
- H2 in-memory database
- Docker multi-stage build

---

## 🛠️ Technologies

### Core
- **Java 21**
- **Spring Boot 4.0.1**
- **Maven 3.9+**

### Spring Modules
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Boot Actuator

### Database
- H2 (in-memory)

### Testing
- JUnit 5
- Spring Boot Test
- MockMvc
- Mockito

### DevOps
- Docker
- Multi-stage Docker build

---

## 🏗️ Architecture

The project follows the **MVC (Model–View–Controller)** pattern:

Client
│
▼
Controller
│
▼
Service
│
▼
Validator ──► Business rules validation
│
▼
Repository
│
▼
H2 Database


### Layers

- **Controller**: Handles HTTP requests and responses
- **Service**: Contains business logic
- **Validator**: Custom domain validations
- **Repository**: Data persistence (JPA)
- **Model**: JPA entities
- **DTO**: Input and output data contracts
- **Exception**: Centralized error handling

---
## 🌐 Endpoints

Base URL: `http://localhost:9000`

### Create Fruit

**POST /fruits**


{
  "name": "Apple",
  "weightInKilos": 5
}

Response: 201 Created

### Get All Fruits

**GET /fruits**

Response: 200 OK

Get Fruit by ID
GET /fruits/{id}

Response: 200 OK
Error: 404 Not Found

### Update Fruit
**PUT /fruits/{id}**


{
  "name": "Green Apple",
  "weightInKilos": 10
}
Response: 200 OK

### Delete Fruit
**DELETE /fruits/{id}**

Response: 204 No Content

Health Check
GET /actuator/health


{
  "status": "UP"
}

---
## 🚀 Installation

Prerequisites
Java 21

Maven 3.9+

Docker (optional)

---
## Clone Project
git clone https://github.com/yvangabrieli/S4.02.SpringBoot-fruit-api-h2
cd S4.02.SpringBoot-fruit-api-h2

---
## Build
mvn clean install
▶️ Execution
Run with Maven
mvn spring-boot:run
Run JAR
java -jar target/fruit-api-h2-0.0.1.jar
Verify
curl http://localhost:9000/actuator/health

---
## 🧪 Tests
The project follows Outside-In TDD.
Run Tests
mvn test
Test Types
Controller integration tests

Service unit tests


---
## 🐳 Docker
Dockerfile (Multi-Stage Build)
Stage 1: Build the JAR using Maven

Stage 2: Run with a lightweight JRE image

Build Image
docker build -t fruit-api:1.0 .
Run Container
docker run -p 9000:9000 fruit-api:1.0

---
## 📁 Project Structure

fruit-api-h2/

---- src/

------ main/

-------- java/

---------- cat/itacademy/s04/t02/n01/

---------- controllers # REST controllers

---------- services # Business logic

---------- repository # JPA Repositories

---------- model # JPA Entities

---------- dto # Request/Response DTOs

---------- mapper # Entity ↔ DTO mapping

---------- validator # Domain validation

---------- exceptions # Exception handling

------- resources/

-------- application.properties

------test/

------java/

---- Dockerfile # Multi-stage Docker build

---- pom.xml # Maven configuration

---- README.md # Project documentation


---
## ✅ Validations
Bean Validation
java
Copiar código
@NotBlank
private String name;

@Positive
private int weightInKilos;
Custom Validator
The validator layer contains domain-specific validation logic that complements Bean Validation and ensures business rules are respected before persistence.

---
## ⚠️ Error Handling
Centralized error handling using @RestControllerAdvice.

Error Response Format

{
  "status": 404,
  "message": "Fruit not found with id: 10",
  "timestamp": "2026-01-10T10:30:00"
}
HTTP Status Codes
Code	Meaning
200	OK
201	Created
204	No Content
400	Bad Request
404	Not Found
500	Internal Server Error

---
## 🔧 Configuration
application.properties
properties
spring.datasource.url=jdbc:h2:mem:fruitdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

management.endpoints.web.exposure.include=health
