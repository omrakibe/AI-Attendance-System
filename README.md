# AI Attendance Management System

An AI-powered Attendance Management System built using Spring Boot Microservices, Spring AI, React, PostgreSQL, and JWT Authentication.

The system aims to simplify attendance management by allowing faculty to mark attendance using natural language prompts and enabling students to access attendance information through an AI-powered assistant.

---

## Project Overview

Traditional attendance systems require manual operations and provide limited insights.

This project introduces AI-driven attendance management where:

- Faculty can mark attendance using natural language prompts.
- Students can interact with an AI assistant to check attendance details.
- Weekly attendance reports are automatically generated and emailed to students.
- The application follows a Microservices Architecture for scalability and maintainability.

---

## Key Features

### Faculty Features

- Faculty Login
- Subject-wise Attendance Management
- AI-based Attendance Marking
- Attendance Reports

### Student Features

- Student Login
- View Subject-wise Attendance
- View Overall Attendance Percentage
- AI Attendance Assistant
- Weekly Attendance Email Reports

### Admin Features

- Student Management
- Faculty Management
- Subject Management
- Role Management

---

## AI Features

### AI Attendance Marking

Faculty can provide prompts such as:

> Mark roll numbers 10 to 20 present and remaining absent.

The AI service converts the prompt into structured attendance commands and updates attendance records automatically.

### AI Attendance Assistant

Students can ask questions such as:

> What is my attendance?

> How many lectures can I miss and still maintain 75% attendance?

> Show my attendance in Java.

The AI service fetches attendance information and generates natural language responses.

---

## Architecture

```text
React Frontend
       |
       v
   API Gateway
       |
       v
   Auth Service
       |
------------------------------------------------
|                     |                         |
v                     v                         v
Student Service   Attendance Service      AI Service
       |                 |                     |
       |<---- Feign ---->|<---- Feign -------->|
       |                 |                     |
       v                 v                     v
 PostgreSQL DB      PostgreSQL DB         Ollama/OpenAI

------------------------------------------------
Infrastructure
------------------------------------------------

Eureka Server
Config Server
Zipkin
Spring Boot Admin
Docker
```

---

## Technology Stack

### Frontend

- React
- Bootstrap

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring AI

### Microservices

- API Gateway
- Eureka Server
- Config Server
- Feign Client

### Database

- PostgreSQL

### AI

- Ollama
- OpenAI (Future Scope)

### Monitoring

- Zipkin
- Spring Boot Admin

### DevOps

- Docker

---

## Services

### Auth Service

Responsibilities:

- Authentication
- Authorization
- JWT Generation
- Role Management

---

### Student Service

Responsibilities:

- Student Profile Management
- Enrollment Management

---

### Attendance Service

Responsibilities:

- Attendance Recording
- Attendance Reports
- Weekly Attendance Email Reports

---

### AI Service

Responsibilities:

- Prompt Processing
- Attendance Query Assistant
- Attendance Command Extraction

---

## User Roles

### ADMIN

- Manage Students
- Manage Faculty
- Manage Subjects

### FACULTY

- Mark Attendance
- View Attendance Reports

### STUDENT

- View Attendance
- Use AI Assistant

---

## Future Enhancements

- Analytics Service
- Kafka Integration
- Attendance Prediction
- Mobile Application
- Voice-Based Attendance Commands
- AI-Powered Attendance Insights
- Cloud Deployment

---

## Project Status

🚧 Currently Under Development

Phase 1:
- Infrastructure Setup
- Authentication
- Student Management
- Attendance Management
- AI Integration

---

---

## License

This project is developed as a Final Year Engineering Project for educational purposes.
