# Task Management API

A RESTful API built with Spring Boot for user authentication and task management.

## Features

- User registration with secure password hashing (BCrypt)
- JWT-based authentication
- CRUD operations for tasks
- User-specific task access control
- Global exception handling
- In-memory H2 database
- Docker support

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- H2 Database
- Maven
- Docker

## Project Structure
```
src/main/java/com/mennakhaliselim/taskmanagementsystem/
├── config/              # Application and security configuration
├── controller/          # REST API endpoints
├── error/
│   └── exception/       # Global exception handling
├── mapper/              # DTO-Entity mapping
├── model/
│   ├── dto/            # Data Transfer Objects
│   ├── entity/         # JPA Entities
│   └── type/           # Enums and custom types
├── repository/          # Data access layer
├── security/
│   ├── exception/      # Security-specific exceptions
│   ├── filter/         # JWT authentication filter
│   ├── model/          # Security domain models
│   └── service/        # Authentication and authorization services
├── service/
│   ├── auth/           # Authentication service layer
│   └── task/           # Task management service layer
├── util/                # Utility classes
└── validation/
    ├── annotation/     # Custom validation annotations
    └── validator/      # Custom validators
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker (optional)

## Getting Started

### Running with Maven

1. Clone the repository:
```bash
git clone https://github.com/mennakhalilselim/task-management-system
cd task-management-system
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

### Running with Docker

1. Build the Docker image:
```bash
docker build -t <image-name> .
```

2. Run the container:
```bash
docker run -p 8080:8080 <image-name>
```

### Postman Collection

A Postman collection is included in the repository (`task-management.postman_collection.json`).

To use it:
1. Import the collection into Postman
2. Register a new user
3. Login to get the JWT token
4. The token will be automatically saved to the collection variables
5. Test the task endpoints

## Security Features

- Passwords are hashed using BCrypt
- JWT tokens for stateless authentication
- Token expiration (24 hours by default)
- CSRF protection disabled for API usage
- CORS enabled for development

## Database

The application uses H2 in-memory database. The database console is available at:
```
http://localhost:8080/h2-console
```

Connection details:
- JDBC URL: `jdbc:h2:mem:task_management_system`
- Username: `sa`
- Password: (empty)