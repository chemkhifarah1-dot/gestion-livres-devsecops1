# Image de base officielle (Eclipse Temurin), JRE seul, variante legere
FROM eclipse-temurin:17-jre-alpine

# Utilisateur non-root dedie a l'application
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

# Le jar est construit par Maven avant la construction de l'image
COPY target/SpringBoot-0.0.1-SNAPSHOT.jar app.jar

# Port d'ecoute dans le conteneur
ENV SERVER_PORT=8080
EXPOSE 8080

USER spring

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "/app/app.jar"]
