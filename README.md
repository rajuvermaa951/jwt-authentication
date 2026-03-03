🔐 Spring Boot JWT Authentication (Stateless)
📌 Overview

This project implements JWT-based authentication from scratch using Spring Boot and Spring Security.

It demonstrates how to build a fully stateless authentication system using:

OncePerRequestFilter

AuthenticationManager

UserDetailsService

SecurityContextHolder

Custom JWT generation & validation

The application does not use sessions, formLogin(), or httpBasic().

🚀 Key Features

✅ User Signup

✅ User Login (Credential Authentication)

✅ JWT Token Generation

✅ JWT Validation via OncePerRequestFilter

✅ Stateless Security Configuration

✅ Role-based Authorization Support

✅ BCrypt Password Encryption

✅ Clean Layered Architecture

🧠 Core Concepts Implemented

Authentication vs Authorization

Spring Security Filter Chain

SessionCreationPolicy.STATELESS

AuthenticationManager

UsernamePasswordAuthenticationToken

UserDetails & UserDetailsService

OncePerRequestFilter

SecurityContextHolder

JWT Structure (Header, Payload, Signature)

🏗️ Project Structure
com.jwt.authentication
│
├── config
│   ├── SecurityConfig.java
│   └── JwtProperties.java
│
├── controller
│   ├── AuthController.java
│   └── TestController.java
│
├── dto
│   ├── LoginRequest.java
│   ├── SignupRequest.java
│   └── AuthResponse.java
│
├── entity
│   └── User.java
│
├── repository
│   └── UserRepository.java
│
├── security
│   ├── JwtUtil.java
│   ├── JwtAuthenticationFilter.java
│   └── CustomUserDetails.java
│
├── service
│   └── CustomUserDetailsService.java
│
└── JwtAuthenticationApplication.java
🔄 Authentication Flow
1️⃣ User Login
POST /auth/login

Request:

{
  "username": "raju",
  "password": "1234"
}

Flow:

Credentials validated using AuthenticationManager

UserDetailsService loads user

JWT generated using JwtUtil

Token returned in response

2️⃣ Access Protected API
GET /api/hello
Authorization: Bearer <JWT_TOKEN>

Flow:

JwtAuthenticationFilter executes (OncePerRequestFilter)

Extracts token from Authorization header

Validates token

Loads user details

Populates SecurityContext

Request proceeds to controller

🔐 Security Configuration

CSRF disabled (REST API)

Stateless session management

Public endpoints: /auth/**

All other endpoints require authentication

Custom JWT filter added before UsernamePasswordAuthenticationFilter

🛠️ Tech Stack

Java 17+

Spring Boot

Spring Security

JPA 

MySQL 

JJWT Library

Maven

❌ Not Used in This Project

❌ HTTP Sessions

❌ formLogin()

❌ httpBasic()

❌ Token storage in database

❌ Server-side authentication state

This project is fully stateless.

🧪 Testing Instructions
Step 1 – Register User
POST /auth/signup
Step 2 – Login
POST /auth/login

Copy the returned JWT token.

Step 3 – Access Protected API
GET /api/hello
Authorization: Bearer <token>
🎯 Learning Outcome

After completing this project, you will understand:

How JWT authentication works internally

How Spring Security processes requests

How SecurityContext is populated manually

How stateless authentication replaces session-based security

🚀 Run the Application
git clone <https://github.com/rajuvermaa951/jwt-authentication.git>
cd jwt-authentication
mvn spring-boot:run


👨‍💻 Author

Raju Verma
Java Backend Developer
Focused on Spring Boot, Spring Security & Scalable Backend Systems             