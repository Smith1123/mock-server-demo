# 1️⃣ Stage: Build
FROM maven:3.9.9-eclipse-temurin-24 AS builder
WORKDIR /app

# Csak a pom.xml másolása először (cache-elhető dependency build miatt)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Most a forráskód
COPY src ./src

# Buildeljük a jar-t
RUN mvn clean package -DskipTests

# 2️⃣ Stage: Runtime
FROM eclipse-temurin:24-jdk
WORKDIR /app

# Átmásoljuk az elkészült JAR-t
COPY --from=builder /app/target/*.jar app.jar

# Profil beállítható kívülről
ENV SPRING_PROFILES_ACTIVE=docker

# Indítás
ENTRYPOINT ["java", "-jar", "app.jar"]

## 1️⃣ Base image
#FROM eclipse-temurin:24-jre
#
## 2️⃣ Environment
#WORKDIR /app
#
## 3️⃣ Copy the built JAR
#COPY target/mock-server-demo-0.0.1-SNAPSHOT.jar app.jar
#
## 4️⃣ Expose the app port
#EXPOSE 8080
#
## 5️⃣ Run the app
#ENTRYPOINT ["java", "-jar", "app.jar"]
