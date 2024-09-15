# Stage 1: Build the application using Maven
FROM maven:3.9.2-eclipse-temurin-17 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Package the application (will create a .jar file)
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight image with the built application
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the .jar file from the build stage to the final stage
COPY --from=build /app/target/VendorHub-0.0.1-SNAPSHOT.jar VendorHub-0.0.1-SNAPSHOT.jar

# Expose the default port Spring Boot runs on
EXPOSE 8080

# Entry point to run the application
ENTRYPOINT ["java", "-jar", "VendorHub-0.0.1-SNAPSHOT.jar"]
