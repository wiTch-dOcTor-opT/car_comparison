# Use the latest LTS version of Amazon Corretto OpenJDK as the base image
FROM amazoncorretto:17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/comparison-0.0.1-SNAPSHOT.jar /app/comparison-0.0.1-SNAPSHOT.jar

# Expose the port that your application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "comparison-0.0.1-SNAPSHOT.jar", "-Dspring.profiles.active=production"]
