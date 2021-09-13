FROM openjdk:14.0.1-slim
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} task.jar
EXPOSE 8080
ENTRYPOINT [ "sh", \
             "-c", \
             "exec java -Xmx768m -Xms256m -jar /task.jar"]
