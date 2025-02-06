# Employee Management System - Microservices Architecture

## Overview

This project is a **Java Spring Boot** application following the **microservices architecture**. It consists of three microservices:

1. **Employee Service** - Manages employee-related operations.
2. **Admin Service** - Handles administrative functionalities.
3. **Registration/Login Service** - Manages user registration.

All services are registered with a **Eureka Server** (Service Registry) and utilize **externalized configuration**.

## Tech Stack

- **Java Spring Boot**
- **Spring Cloud (Eureka Server, Config Server)**
- **Spring Data JPA**
- **H2 Database (In-Memory Database)**
- **Maven (Build Tool)**
- **Microservices Architecture**
- **Spring MVC, HTML, Thymeleaf (Frontend)**

## Project Structure

```
|-- employee_service
|-- admin_service
|-- registration_service
|-- service_registry (Eureka Server)
|-- config_server (Externalized Configuration)
```

## Features

✅ Microservices-based architecture  
✅ Centralized configuration using Config Server  
✅ Service discovery with Eureka  
✅ In-memory H2 database for persistence  
✅ Frontend built using Spring MVC and Thymeleaf  

## Prerequisites

- JDK 17+
- Maven

## Running the Application

1. **Clone the repository:**

   ```sh
   git clone https://github.com/arman-malik9/emp-management.git
   cd your-repo
   ```

2. **Start the Eureka Server:**

   ```sh
   mvn spring-boot:run
   ```

3. **Start the Config Server:**

   ```sh
   mvn spring-boot:run
   ```

4. **Start the Microservices:**

   ```sh
   cd employee_service && mvn spring-boot:run
   cd ../admin_service && mvn spring-boot:run
   cd ../registration_service && mvn spring-boot:run
   ```

## Frontend Interface

The frontend of this application is built using **Spring MVC and Thymeleaf**. It provides a user-friendly interface to interact with the microservices.

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss the modifications.

