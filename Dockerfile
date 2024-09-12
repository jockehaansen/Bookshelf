FROM openjdk:17-jdk-alpine as build
EXPOSE 8080
ADD target/Bookshelf-1.jar Bookshelf-1.jar
ENTRYPOINT ["java", "-jar", "/Bookshelf-1.jar "]