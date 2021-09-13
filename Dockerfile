FROM openjdk:14.0.1-slim
VOLUME /tmp
ADD build/libs/task.jar task.jar
EXPOSE 8080
ENTRYPOINT [ "sh", \
             "-c", \
             "exec java -Xmx768m -Xms256m -jar task.jar"]

