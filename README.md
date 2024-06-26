﻿# Event_Management_System
Event Management System

API documentation for the provided endpoints /events/find and /events.
1. /events/find Endpoint:
•	Description: Retrieve a list of events based on user latitude, longitude, and date.
•	Method: GET
•	URL: /events/find
•	Request Parameters:
•	userLatitude (String, required): Latitude of the user's location.
•	userLongitude (String, required): Longitude of the user's location.
•	date (String, required): Date of the event in the format 'YYYY-MM-DD'.
•	Response:
•	HTTP Status Code: 200 OK
•	Body: List of EventDto objects representing events found.
•	eventName (String): Name of the event.
•	cityName (String): Name of the city where the event is taking place.
•	date (String): Date of the event in the format 'YYYY-MM-DD'.
•	weather (String): Weather information for the event location.
•	distance (String): Distance between the user's location and the event location.
•	Error Responses:
•	HTTP Status Code: 400 Bad Request
•	Body: Empty or null response body.

2. /events Endpoint:
•	Description: Add a new event.
•	Method: POST
•	URL: /events
•	Request Body:
•	JSON Object representing the event to be added. 
•	Example:jsonCopy code
Explain
{ "eventName": "Event Name", 
"cityName": "City Name", 
"date": "YYYY-MM-DD", 
"time": "HH:MM:SS",
 "latitude": "Latitude", 
"longitude": "Longitude" } 
•	Response:
•	HTTP Status Code: 201 Created
•	Body: Success message indicating the event was added successfully.
•	Error Responses:
•	HTTP Status Code: 400 Bad Request
•	Body: Empty or null response body.
Error Codes:
•	400 Bad Request: Indicates invalid request parameters or malformed request body.





Justification and Reflection Report
Choice of Technology Stack and Database:
1.	Java Spring Boot:
•	Spring Boot provides a robust framework for building scalable and production-ready applications with minimal configuration.
•	Its convention-over-configuration approach reduces boilerplate code and accelerates development.
•	Spring Boot's extensive ecosystem offers built-in support for various features like security, data access, and RESTful services.
•	Dependency management with Spring Boot's Starter POMs simplifies project setup and management.
2.	MySQL Database:
•	MySQL is a widely used relational database management system known for its performance, reliability, and ease of use.
•	It provides strong ACID compliance, making it suitable for transactional applications.
•	MySQL's compatibility with various platforms and its popularity in the industry ensure good community support and resources.
•	It offers features like indexing, replication, and clustering for scalability and high availability.
Design Decisions:
1.	RESTful API with Spring Boot:
•	Chose Spring Boot for building a RESTful API due to its simplicity and productivity features.
•	Utilized Spring Web MVC for handling HTTP requests and responses, providing a clean and organized approach for building APIs.
2.	JPA for Data Access:
•	Leveraged Spring Data JPA for database access, enabling easy integration with MySQL database through simple repository interfaces.
•	Used JPA annotations for entity mapping and defining relationships, reducing boilerplate code and enhancing readability.
    Challenges and Solutions:
1.	Performance Optimization:
•	Identified potential performance bottlenecks, such as database queries and external API calls.
•	Addressed these by implementing pagination for database queries, indexing frequently accessed columns, and utilizing parallel streams for concurrent processing.
2.	Error Handling and Resilience:
•	Implemented robust error handling mechanisms to gracefully handle exceptions and failures, ensuring the application remains resilient.
•	Used try-catch blocks to catch and handle exceptions, logging errors for diagnostic purposes and providing appropriate error responses to clients.














Here are the instructions to set up and run the project:
Prerequisites:
1.	JDK (Java Development Kit) installed on your system. You can download it from the official Oracle website or use OpenJDK.
2.	Apache Maven installed to build and manage dependencies. You can download it from the Apache Maven website.
Steps to Run the Project:
1. Clone the Repository:
git clone <https://github.com/mrchilumula/Event_Management> 
2. Navigate to the Project Directory:
cd <project_directory> 
3. Build the Project:
mvn clean package 
This command will compile the source code, run tests, and package the application into a JAR file.
4. Set Up MySQL Database:
•	Ensure MySQL is installed on your system.
•	Create a database for the application. You can use the MySQL command-line client or a GUI tool like MySQL Workbench.
•	Update the database configuration in the application.properties file to match your MySQL database settings.
5. Run the Application:
java -jar target/< >.jar
