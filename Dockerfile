# Build Stage
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY . .

RUN mvn clean install

# Production Stage
FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=build /app/target/DataFakerDemo-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8009

CMD ["java", "-jar", "app.jar"]
