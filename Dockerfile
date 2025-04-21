# Stage 1: Build the application with Gradle
FROM eclipse-temurin:23-jdk-alpine as builder
WORKDIR /app

# Copy Gradle files separately to leverage Docker caching
COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./
RUN ./gradlew build -x test || return 0

# Copy the rest of the source and build again
COPY . .
RUN ./gradlew clean build -x test

# Stage 2: Run the application
FROM eclipse-temurin:23-jdk-alpine
VOLUME /tmp

ARG JAR_FILE=app.jar
COPY --from=builder /app/build/libs/*.jar ${JAR_FILE}

ENTRYPOINT ["java", "-jar", "app.jar"]
