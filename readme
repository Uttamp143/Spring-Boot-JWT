# Spring Boot 3 + JWT Authentication (JJWT 0.12.x) – Complete Project Guide

This project demonstrates a **production‑grade JWT Authentication system** using:

* **Spring Boot 3.x**
* **Spring Security 6**
* **JJWT 0.12.5** (latest + Jakarta compatible)
* **H2 Database**

It includes:

* User Registration
* Login (JWT Generation)
* Authentication Filter
* Secure Endpoints
* Token Validation
* Password Hashing (BCrypt)

---

## 🚀 Features

### ✔ Register User

Creates a new user with encrypted password.

### ✔ Login with Username & Password

Generates a signed JWT containing user details.

### ✔ Access Private Endpoints

Accessible only with a valid JWT in:

```
Authorization: Bearer <token>
```

### ✔ Stateless Authentication

No session state stored on server.

---

## 🛠 Tech Stack

* **Java 17**
* **Spring Boot 3.x**
* **Spring Security**
* **Spring Data JPA**
* **H2 in‑memory database**
* **JJWT 0.12.5**

---

## 📁 Project Structure

```
src/main/java/com/example/demo
│
├── controller
│   ├── AuthController.java
│   └── HelloController.java
│
├── model
│   └── User.java
│
├── payload
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   └── RegisterRequest.java
│
├── repository
│   └── UserRepository.java
│
├── security
│   ├── JwtUtil.java
│   ├── JwtAuthenticationFilter.java
│   ├── SecurityConfig.java
│   └── CustomUserDetailsService.java
│
└── SpringBootJwtApplication.java
```

---

## 📦 Dependencies

Key dependencies in `pom.xml`:

### Spring Boot

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### JJWT (latest compatible)

To work correctly with Spring Boot 3 (Jakarta), JJWT 0.12.x requires **three dependencies**:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
```

Each one has a purpose:

* **jjwt-api** → Contains public JWT interfaces
* **jjwt-impl** → Performs signing, parsing, validation
* **jjwt-jackson** → JSON serialization/deserialization using Jackson

  <dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>

</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
```

---

## ⚙ Configuration

### `application.properties`

```
spring.datasource.url=jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

app.jwt-secret=verySecretKeyForHS256_ChangeThisInProd
app.jwt-expiration-ms=900000
```

---

## 🔐 Authentication Flow

1. **User registers** → Password encrypted using BCrypt
2. **User logs in** → Server generates JWT
3. **Client stores token** (localStorage/mobile secure storage)
4. **Client sends token** in header:

   ```
   Authorization: Bearer <token>
   ```
5. **Filter validates token** (signature + expiry)
6. **SecurityContext updated** → Access granted

---

## 📮 API Endpoints

### ▶ Register

```
POST /api/auth/register
Content-Type: application/json

{
  "username": "uttam",
  "password": "12345"
}
```

➡ Response: `User registered`

---

### ▶ Login (Get JWT)

```
POST /api/auth/login
Content-Type: application/json

{
  "username": "uttam",
  "password": "12345"
}
```

➡ Response:

```
{
  "token": "<JWT_TOKEN>"
}
```

---

### ▶ Public Endpoint

```
GET /api/public
```

➡ `Hello - public`

---

### ▶ Private Endpoint (Requires JWT)

```
GET /api/private
Authorization: Bearer <token>
```

➡ `Hello - private (authenticated)`

---

## 🔒 Security Notes

* Always use **HTTPS** in production.
* Use **long secret keys** for HS256.
* Do **not** store JWT in browser localStorage (vulnerable to XSS).
* Prefer **HTTPOnly Secure Cookie** or secure App storage.
* Implement **Refresh Token** for long sessions.
* Consider **RS256** (public/private key) for microservices.

---

## ▶ Running the Project

```
mvn clean install
mvn spring-boot:run
```

Or run from your IDE.

---

## 💬 Need Enhancements?

I can generate:

* Refresh token implementation
* Logout + token blacklist
* Role‑based authorization
* RS256 key‑pair JWT version
* Dockerfile + docker‑compose
* Postman Collection

Just ask! 🚀
