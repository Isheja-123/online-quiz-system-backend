# Online Quiz System Backend

**Course:** INSY 8322 Web Technology and Internet  
**Student Name:** Umuhire Isheja Delice  
**Student ID:** 27832  
**Group Day:** Tuesday  
**Project:** Online Quiz System (`online-quiz-system-backend`)

---

## 1. Project Overview
This project is a RESTful API backend for the **Online Quiz System**, built using Spring Boot, Spring Data JPA, and PostgreSQL. It manages students, instructors, subjects, labs, quiz attempts (with status transitions), and instructor feedback notes.

---

## 2. Requirements & Prerequisites
- Java Development Kit (JDK 21 or higher, developed and tested on JDK 25)
- Apache Maven (version 3.9+)
- PostgreSQL Database Server

---

## 3. Database Configuration
1. Start your PostgreSQL server.
2. In pgAdmin or SQL Shell (`psql`), create the database:
   ```sql
   CREATE DATABASE quizdb;
   ```
3. Check the database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/quizdb
   spring.datasource.username=postgres
   spring.datasource.password=12345

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   server.port=8082
   ```

---

## 4. How to Run the Application

### Option A: Using Maven from Terminal
1. Open the project root folder in terminal or VS Code:
   ```powershell
   cd C:\Users\user\Desktop\online-quiz-system-backend
   ```
2. Compile and package the project:
   ```powershell
   mvn clean compile
   ```
3. Run the Spring Boot application:
   ```powershell
   mvn spring-boot:run
   ```
4. The backend server will start on port **8082**: `http://localhost:8082`.

### Option B: Using VS Code
- Open `src/main/java/rw/ac/auca/onlinequiz/OnlineQuizApplication.java`.
- Click the **Run** button above the `main` method.

---

## 5. Testing with Postman
The Postman collection is exported and available at the root of the repository:
📁 `online-quiz-system-midterm.postman_collection.json`

### How to Import & Test:
1. Open Postman.
2. Click **Import** and select the file `online-quiz-system-midterm.postman_collection.json`.
3. Every requirement from the examination is organized and named by its ID:
   - **Part A:** A2 CRUD endpoints (`/save`, `/all`, `/{id}`, `/update/{id}`, `/delete/{id}`)
   - **Part B:** B1 (`/by-last-name`), B2 (`/by-status`), B3 (`/between`)
   - **Part C:** C1 (`/by-subject`), C2 (`/free`), C3 (`/unused`), C4 (`/of-instructor/{id}`)
   - **Part D:** D1 (Safe saving), D2 (Status flow), D3 (Add subject), D4 (Move lab)
   - **Part E:** E1 (Write feedback note), E2 (Student feedback history), E3 (Delete & protect)
   - **Part F:** F1 (Stats by status), F2 (Cancel day)
   - **Bonus:** Bonus 1 (`/dashboard/summary`), Bonus 2 (`/page`)
