FROM alpine:3.13

RUN apk add openjdk8-jre
COPY target/delivery-system-1.jar /app.jar
ADD uploads target/classes/

ENTRYPOINT ["java", "-jar", "/app.jar"]
