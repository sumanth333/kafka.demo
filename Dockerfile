FROM openjdk:11
EXPOSE 8080
ADD build/libs/kafka.demo-0.0.1-SNAPSHOT.jar kafka-demo-0.0.1.jar
ENTRYPOINT ["java","-jar","/kafka-demo-0.0.1.jar"]