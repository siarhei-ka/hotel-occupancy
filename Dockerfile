# Use Eclipse Temurin Java 24 base image
FROM eclipse-temurin:24-jdk-jammy

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Make the wrapper script executable
RUN chmod +x mvnw run.sh

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/hotel-occupancy-1.0.0.jar"]