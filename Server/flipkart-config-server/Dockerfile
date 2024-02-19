FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/flipkart-config-server-0.0.1-SNAPSHOT.jar flipkart-config-server.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","flipkart-config-server.jar"]