FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /build
# copy only files needed to download dependencies first (cache friendly)
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN mvn -B -f pom.xml dependency:go-offline

# copy sources and build
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
# copy the built jar into a clear location
COPY --from=builder /build/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]