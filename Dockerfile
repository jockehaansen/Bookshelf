# Use an official OpenJDK image as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and permission for execution
COPY gradlew ./
RUN chmod +x gradlew

# Copy the gradle folder
COPY gradle ./gradle

# Copy the project files
COPY . .

RUN ls -la

# Build the application using Gradle
RUN ./gradlew build -x test

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "build/libs/Bookshelf-1.jar"]
