# 🎓 Student Management System

A backend application built using **Spring Boot** that provides RESTful APIs to manage student records efficiently. The system supports CRUD operations, search functionality, and follows a clean layered architecture using Spring MVC and Spring Data JPA.

---

## 🚀 Features

- Create, update, delete, and retrieve student records  
- Search students by name (case-insensitive)  
- RESTful API design using Spring MVC  
- Data persistence using Spring Data JPA  
- Clean layered architecture (Controller → Service → Repository)  
- Cross-Origin support for frontend integration  

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC  
- **Database:** JPA (Hibernate)  
- **Persistence:** Spring Data JPA  
- **Build Tool:** Maven  
- **API Testing:** Postman  

---

## 📁 Project Structure

```
com.studentmanagement  
│  
├── controller        # Handles HTTP requests  
├── service           # Business logic layer  
├── repository        # Data access layer (JPA)  
├── model             # Entity classes  
└── StudentManagementSystemApplication.java  

```

---

## 🔗 API Endpoints

| Method | Endpoint                  | Description                |
|--------|--------------------------|----------------------------|
| GET    | /api/students            | Get all students           |
| GET    | /api/students/{id}       | Get student by ID          |
| POST   | /api/students            | Create new student         |
| PUT    | /api/students/{id}       | Update existing student    |
| DELETE | /api/students/{id}       | Delete student             |
| GET    | /api/students/search     | Search students by name    |

---

## 🧩 Entity Overview

The system manages student data including:
- Name  
- Email (unique)  
- Phone  
- Course  
- Enrollment Date  

---

## ⚙️ How It Works

1. Controller Layer handles incoming HTTP requests  
2. Service Layer processes business logic  
3. Repository Layer interacts with the database using JPA  
4. Data is stored and retrieved using entity mapping  

---

## ▶️ Run the Application

# Clone the repository
git clone <your-repo-url>

# Navigate to project
cd student-management-system

# Run the application
mvn spring-boot:run

---

## 📌 Future Enhancements

- Add authentication & authorization (Spring Security)  
- Integrate frontend (React/Angular)  
- Add pagination and sorting  
- Implement validation and logging  

---
