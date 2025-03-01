# Task Manager API

Task Manager API is a RESTful API developed with Spring Boot for managing tasks and task categories. The API allows the creation, retrieval, updating, and deletion of tasks and categories using a MySQL database.

## Requirements

- Java 17+
- Maven 3+
- MySQL 8+

## Database Configuration

Create the database in MySQL:

```sql
CREATE DATABASE taskdb;
```

Configure the `src/main/resources/application.properties` file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace `root` and `1234` with your database credentials.

## Installation and Execution

```bash
git clone this repository
cd task-manager-api
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/`.

## API Endpoints

### Task Endpoints (`/tasks`)

#### Create a New Task
- **URL:** `POST /tasks`
- **Request JSON:**
```json
{
    "title": "Complete documentation",
    "description": "Review and correct project documentation",
    "status": "PENDING",
    "categoryId": 1
}
```
- **Response (200 OK):**
```json
{
    "id": 1,
    "title": "Complete documentation",
    "description": "Review and correct project documentation",
    "status": "PENDING",
    "categoryId": 1
}
```

#### Get All Tasks
- **URL:** `GET /tasks`
- **Response:**
```json
[
    {
        "id": 1,
        "title": "Complete documentation",
        "description": "Review and correct project documentation",
        "status": "PENDING",
        "categoryId": 1
    }
]
```

#### Get Task by ID
- **URL:** `GET /tasks/{id}`
- **Response:**
```json
{
    "id": 1,
    "title": "Complete documentation",
    "description": "Review and correct project documentation",
    "status": "PENDING",
    "categoryId": 1
}
```

#### Update a Task
- **URL:** `PUT /tasks/{id}`
- **Request JSON:**
```json
{
    "title": "Review documentation",
    "description": "Update outdated information",
    "status": "IN_PROGRESS",
    "categoryId": 1
}
```

#### Update Task Status Only
- **URL:** `PUT /tasks/{id}/status?status=COMPLETED`
- **Response:**
```json
{
    "id": 1,
    "title": "Review documentation",
    "description": "Update outdated information",
    "status": "COMPLETED",
    "categoryId": 1
}
```

#### Delete a Task
- **URL:** `DELETE /tasks/{id}`
- **Response:**
```json
{
    "message": "Task with ID 1 has been successfully deleted."
}
```

### Category Endpoints (`/tasks/categories`)

#### Create a New Category
- **URL:** `POST /tasks/categories`
- **Request JSON:**
```json
{
    "name": "Work"
}
```
- **Response:**
```json
{
    "message": "Category created successfully.",
    "id": "1"
}
```

#### Get All Categories
- **URL:** `GET /tasks/categories`
- **Response:**
```json
[
    {
        "id": 1,
        "name": "Work"
    }
]
```

#### Get Category by ID
- **URL:** `GET /tasks/categories/{id}`
- **Response:**
```json
{
    "id": 1,
    "name": "Work"
}
```

#### Update a Category
- **URL:** `PUT /tasks/categories/{id}`
- **Request JSON:**
```json
{
    "name": "Business"
}
```

#### Delete a Category
- **URL:** `DELETE /tasks/categories/{id}`
- **Response:**
```json
{
    "message": "Category with ID 1 has been successfully deleted."
}
```


