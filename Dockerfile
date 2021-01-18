FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Orderapp.jar
ENTRYPOINT ["java","-jar","/Orderapp.jar"]