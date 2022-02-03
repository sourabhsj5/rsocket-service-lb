FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app

ADD ./target/rsocket-server-svc-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 6565
ENTRYPOINT ["java", "-jar", "app.jar"]