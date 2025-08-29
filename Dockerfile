# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper and make it executable
COPY .mvn/ .mvn
COPY mvnw ./
RUN chmod +x mvnw

# Copy pom.xml and download dependencies in one step
COPY pom.xml ./
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/e-commerce-0.0.1-SNAPSHOT.jar"]