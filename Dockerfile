# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk
RUN microdnf install findutils
# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY build/libs/chatbot-0.0.1-SNAPSHOT.jar chatbot.jar

# Make port 8081 available to the world outside this container

# Define the command to run the application
CMD ["java", "-jar", "/app/chatbot.jar"]