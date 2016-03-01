FROM java:8
VOLUME /tmp
ADD target/feed-app-0.1.0.jar app.jar
ADD application.properties application.properties
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar","/app.jar"]
