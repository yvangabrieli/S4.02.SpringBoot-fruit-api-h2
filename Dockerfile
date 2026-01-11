# ===============================
# Stage 1 — Build
# ===============================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only pom.xml first to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true -B


# ===============================
# Stage 2 — Runtime
# ===============================
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="itacademy@example.com"
LABEL description="Fruit API REST with Spring Boot and H2"

WORKDIR /app

# Copy only the generated JAR from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]
