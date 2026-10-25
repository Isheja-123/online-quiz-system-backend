# Online Quiz System Backend

**Course:** INSY 8322 Web Technology and Internet  
**Student Name:** Umuhire Isheja Delice  
**Student ID:** 27832  
**Project:** Online Quiz System  

---

## Description
This is a Spring Boot REST API for the Online Quiz System developed with Spring Data JPA and PostgreSQL. It manages students, instructors, subjects, labs, quiz attempts, and instructor feedback notes.

---

## Prerequisites
- Java Development Kit (JDK 21 or later, tested on JDK 25)
- Apache Maven 3.9+
- PostgreSQL database server running locally

---

## Database Configuration
1. Open PostgreSQL (pgAdmin or psql) and create the database:
   ```sql
   CREATE DATABASE quizdb;
   ```
2. Check or update `src/main/resources/application.properties` with your PostgreSQL username and password:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/quizdb
   spring.datasource.username=postgres
   spring.datasource.password=12345
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   server.port=8082
   ```

---

## How to Run the Application
1. Open a terminal in the project root directory.
2. Build the project:
   ```bash
   mvn clean compile
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The API will be available at `http://localhost:8082`.
