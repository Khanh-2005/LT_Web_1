# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/studentmanager-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render will provide PORT env variable)
EXPOSE 8080

# Set environment variables for database connection
ENV PORT=8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
