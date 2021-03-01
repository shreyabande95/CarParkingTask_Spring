FROM openjdk:12
ADD target/spring-mongo-docker.jar spring-mongo-docker.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","spring-mongo-docker.jar"]