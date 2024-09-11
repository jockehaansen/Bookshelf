# Stage 1: Build the application
FROM gradle:8.0-jdk11 AS build

WORKDIR /app

# Copy Gradle files and download dependencies
COPY gradle /app/gradle
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon

# Copy the source code and build the application
COPY src /app/src
RUN gradle build --no-daemon

# Stage 2: Create a minimal runtime image
FROM openjdk:20-jdk-slim

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]