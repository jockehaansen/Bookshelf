#Frontend build
FROM node:18 AS frontend-build
WORKDIR /app/frontend/bookshelf
COPY frontend/bookshelf/package.json ./
COPY frontend/bookshelf/package-lock.json ./
RUN npm install
COPY frontend/bookshelf ./
RUN npm run build

#Backend
FROM openjdk:17-jdk-alpine AS backend-build
WORKDIR /app/backend
COPY src ./src
COPY build.gradle .
COPY settings.gradle .
COPY gradlew ./
COPY gradle/wrapper/gradle-wrapper.jar ./gradle/wrapper/
COPY gradle/wrapper/gradle-wrapper.properties ./gradle/wrapper/
RUN chmod +x gradlew
RUN ./gradlew build

# Build the React app
RUN npm run build

# Use Nginx to serve the built app
FROM nginx:alpine

# Copy built files to Nginx directory
COPY --from=dist /app/build /usr/share/nginx/html

# Copy the Nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose the port
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]

#Create final image
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=backend-build /app/backend/build/libs/Bookshelf-*.jar app.jar
COPY --from=frontend-build /app/frontend/bookshelf/dist ./frontend/dist
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]





# Use an official OpenJDK image as a parent image
#FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
#WORKDIR /app

# Copy the Gradle wrapper and permission for execution
#COPY gradlew ./
#RUN chmod +x gradlew

# Copy the gradle folder
#COPY gradle ./gradle

# Copy the project files
#COPY . .

# Build the application using Gradle
#RUN ./gradlew build -x test

# Expose the port your app runs on
#EXPOSE 8080

# Run the Spring Boot app
#ENTRYPOINT ["java", "-jar", "build/libs/Bookshelf-1.jar"]
