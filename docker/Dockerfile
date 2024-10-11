# Set up image
#FROM openjdk:17-jdk-slim
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

RUN mkdir /application
WORKDIR /application

RUN apt update
RUN apt install gradle -y


# Compile application
COPY ./ .
RUN chmod +x ./gradlew
EXPOSE 8080

# Command to run the application using the generated JAR file
CMD ["java", "-jar", "build/libs/untitled-1.0-SNAPSHOT.jar"]