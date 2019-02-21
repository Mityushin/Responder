FROM openjdk:8-jdk-alpine
ADD target/Responder.jar Responder.jar
VOLUME /var/opt/responder
VOLUME /var/log/responder
EXPOSE 8090
CMD ["java", "-jar", "Responder.jar"]
