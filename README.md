Flight Reservation System is a backend application developed using Spring Boot, Spring Security, Spring Data JPA, and MySQL. It provides REST APIs to handle user authentication, flight management, and booking management. Users can register, log in securely, search for flights by source, destination, and date, book flights with passenger details, and view or cancel their reservations. Admins can add, update, and delete flights, manage users, and view all bookings.

The application uses Spring Security for authentication and authorization, with BCrypt encryption for storing passwords securely. Role-based access is implemented where normal users can only manage their own bookings, while admins have access to manage flights and oversee all reservations.

The database is managed with MySQL, and Spring Data JPA (Hibernate) is used for ORM to interact with the database. The project is structured with separate layers for controllers, entities, DTOs, repositories, services, and configuration.

This backend system is designed to be scalable and can be extended with features like JWT authentication, payment gateway integration, email or SMS booking notifications, and seat selection in the future.

Developed by Pydi Praveen Kumar.
