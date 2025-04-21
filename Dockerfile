# Stage 1: Build the application with Gradle
FROM eclipse-temurin:23-jdk-alpine as builder
WORKDIR /app

# Copy the Gradle config and wrapper first for caching
COPY gradlew build.gradle settings.gradle ./
COPY gradle gradle

# Now copy source files
COPY src src

# Now run build
RUN ./gradlew build -x test

# Second stage: run the app
FROM eclipse-temurin:23-jdk-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
