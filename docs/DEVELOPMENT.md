# Development Setup Guide

This document provides instructions on setting up and running the e-commerce project locally.

## Required Software Versions

To run this project successfully, you need to have the following software installed with the specified versions:

| Software | Version | Purpose |
|----------|---------|---------|
| Java | 21 | Main programming language |
| Maven | 3.9+ | Build tool |
| MySQL | 8.0.43 | Database |
| Docker | Latest | Containerization (optional but recommended) |
| Docker Compose | Latest | Multi-container management (optional but recommended) |

## Project Dependencies

The project uses the following major dependencies:

- Spring Boot 3.5.5
- Spring Modulith 1.4.1
- Flyway 10.21.0
- MapStruct 1.5.5.Final
- Lombok 1.18.34
- MySQL Connector/J

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd e-commerce
```

### 2. Configure Environment Variables

Copy the example environment file and adjust the values as needed:

```bash
cp .env.example .env
```

Update the values in the `.env` file according to your environment.

### 3. Database Setup

You have two options for setting up the database:

#### Option A: Using Docker (Recommended)

Start the MySQL database using Docker Compose:

```bash
docker-compose up -d mysql
```

This will start a MySQL 8.0.43 container with the configuration specified in the `docker-compose.yml` file.

#### Option B: Using Local MySQL Installation

Install MySQL 8.0.43 on your local machine and create a database with the credentials specified in the `application.yml` file.

### 4. Configure Database Connection

The application uses the following default database configuration:

- URL: `jdbc:mysql://localhost:3310/e_store`
- Username: `saif`
- Password: `pass1234`

These values can be overridden using environment variables:
- `MYSQL_PORT` (default: 3310)
- `MYSQL_DATABASE` (default: e_store)
- `MYSQL_USER` (default: saif)
- `MYSQL_PASSWORD` (default: pass1234)

### 5. Run Database Migrations

Flyway is used for database migrations. The migrations are located in `src/main/resources/db/migration`.

When the application starts, Flyway will automatically run the migrations if `spring.flyway.enabled=true` (which is the default in the application configuration).

### 6. Build and Run the Application

#### Using Maven Wrapper

To build and run the application:

```bash
./mvnw spring-boot:run
```

#### Using Maven

If you have Maven installed:

```bash
mvn spring-boot:run
```

#### Using Java

To build and run as a jar file:

```bash
./mvnw clean package
java -jar target/e-commerce-0.0.1-SNAPSHOT.jar
```

### 7. Access the Application

Once the application is running, you can access it at: http://localhost:8080

## Development Tools

### Hot Reloading

The application includes Spring Boot DevTools which provides hot reloading capabilities. Any changes to the code will automatically restart the application.

### IDE Configuration

For the best development experience, configure your IDE with:
1. Lombok plugin
2. MapStruct plugin (if available)
3. Set Java version to 21

Make sure annotation processing is enabled in your IDE to work with Lombok and MapStruct.

## Troubleshooting

### Common Issues

1. **Database Connection Issues**
   - Ensure MySQL is running and accessible on the configured port
   - Check that the database credentials are correct
   - Verify that the database `e_store` exists

2. **Port Conflicts**
   - If port 3310 or 8080 is already in use, you can change them using environment variables:
     - For MySQL port: Set `MYSQL_PORT` in your `.env` file
     - For application port: Set `SERVER_PORT` in your environment

3. **Missing Dependencies**
   - Run `./mvnw clean install` to ensure all dependencies are properly downloaded and configured

4. **Flyway Migration Issues**
   - If you encounter migration issues, you can try cleaning the database with:
     ```bash
     ./mvnw flyway:clean
     ```
   - Then restart the application to re-run the migrations

### Useful Commands

- Check application health: `curl http://localhost:8080/actuator/health`
- View application info: `curl http://localhost:8080/actuator/info`