FROM openjdk:21

COPY identity-*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]