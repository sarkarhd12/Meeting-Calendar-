# Meeting-Calendar

Meeting Calendar Assistant
Overview

The Meeting Calendar Assistant is a Spring Boot application designed to help manage meeting scheduling and conflicts between employees. It provides RESTful API endpoints to book meetings, find free slots, and check for scheduling conflicts.
Features

    Book Meetings: Schedule a meeting for employees.
    Find Free Slots: Get available time slots for two employees based on a specified duration.
    Check Conflicts: Identify conflicts in meeting schedules for participants.

Prerequisites

    Java 11 or higher
    Maven 3.x
    Postman for API testing

Project Structure

The project follows a layered architecture, consisting of the following components:

    Controller: Manages HTTP requests and responses.
    Service: Contains the business logic for managing meetings.
    Repository: Handles data persistence and retrieval.
    Model: Defines the data structures used in the application.

Installation

    Clone the repository:

    bash

git clone <repository-url>
cd meeting_calendar_assistant

Build the project:

bash

mvn clean install

Run the application:

bash

    mvn spring-boot:run

    The application will start on http://localhost:8080.

API Endpoints
1. Book a Meeting

    Endpoint: /api/meetings/book

    Method: POST

    Request Body:

    json

    {
        "id": "meeting1",
        "startTime": "2024-10-21T10:00:00",
        "endTime": "2024-10-21T11:00:00",
        "participants": ["employee1", "employee2"]
    }

    Response:
        Status 200 OK
        Body: The booked meeting details

2. Find Free Slots

    Endpoint: /api/meetings/free-slots

    Method: GET

    Parameters:
        employeeId1: First employee ID
        employeeId2: Second employee ID
        duration: Duration in minutes

    Example:

    sql

    GET /api/meetings/free-slots?employeeId1=employee1&employeeId2=employee2&duration=30

    Response:
        Status 200 OK
        Body: List of available slots (LocalDateTime)

3. Check Conflicts

    Endpoint: /api/meetings/conflicts

    Method: POST

    Request Body:

    json

    {
        "id": "meeting2",
        "startTime": "2024-10-21T10:30:00",
        "endTime": "2024-10-21T11:30:00",
        "participants": ["employee1", "employee3"]
    }

    Response:
        Status 200 OK
        Body: List of participants with conflicts

Design Patterns Used

    Repository Pattern: Abstracts data access and promotes a clean separation between the data layer and the business logic.
    Service Layer: Encapsulates the business logic, providing a clear API for the controller.
    Data Transfer Objects (DTOs): Used for transferring data between layers.

Testing

The application follows Test-Driven Development (TDD) principles. JUnit is used for testing business logic and data access layers.
Running Tests

    Run the tests using Maven:

    bash

    mvn test

    Tests are located in the src/test/java directory and cover:
        Business logic in the MeetingService class.
        DAO layer functionality in the MeetingRepository class.

