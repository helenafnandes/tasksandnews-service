# TasksAndNews Service

This is the backend service for the TasksAndNews application, a simple REST API built with Spring Boot that allows users to manage their tasks.

The API stores and retrieves data from a database using PostgreSQL and has been containerized using Docker for easy deployment and management. The project includes tests for the API endpoints using JUnit and MockMvc.

The frontend for the application can be found in this repository: [TasksAndNews WebApp](https://github.com/helenafnandes/tasksandnews-frontend)

## API Documentation

| **Endpoint**             | **Method** | **Description**                       | **Request Body**                                                                                                                                       | **Response**                                                                                                           |
|--------------------------|------------|---------------------------------------|---------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| `/tasks`                 | `POST`     | Create a new task                     | ```json { "title": "Task title", "description": "Task description" } ```                                                                                | `201 Created`                                                                                                          |
| `/tasks`                 | `GET`      | Get all tasks                         | N/A                                                                                                                                                     | ```json [ { "id": 1, "title": "Task title", "description": "Task description" } ] ```                                  |
| `/tasks/{id}`            | `GET`      | Get a task by ID                      | N/A                                                                                                                                                     | ```json { "id": 1, "title": "Task title", "description": "Task description" } ```                                      |
| `/tasks/{id}`            | `PUT`      | Update a task                         | ```json { "title": "Updated title", "description": "Updated description" } ```                                                                          | `200 OK`                                                                                                               |
| `/tasks/{id}`            | `DELETE`   | Delete a task                         | N/A                                                                                                                                                     | `204 No Content`                                                                                                       |


## Installation and Setup

### Running Locally with Docker

0. Prerequisites: Have Docker and Docker Compose installed and running.

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/helenafnandes/tasksandnews-service.git
    cd tasksandnews-service
    ```

2. **Build and Run the Containers**:
    ```bash
    docker-compose up --build
    ```

3. **Access the Application**:
    - The backend service will be available at `http://localhost:8090`.

4. **Stopping the Containers**:
    ```bash
    docker-compose down
    ```

### Running Locally without Docker

To run the service directly:

1. Ensure you have PostgreSQL installed and running.
2. Set up a database with the name `tasksandnews`, and create a user `tasksandnewsAdmin` with the password `tasksandnewsAdmin`.
3. Update the `application.properties` file with your local PostgreSQL configuration.
4. Run the application with Maven:
    ```bash
    mvn spring-boot:run
    ```

