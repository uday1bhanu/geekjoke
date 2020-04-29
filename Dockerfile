FROM openjdk:11-jdk
LABEL maintainer="Uday"

COPY /build/libs/geekjoke-0.0.1-SNAPSHOT.jar /

EXPOSE 9092

ENV JAVA_OPTIONS ""
CMD ["sh", "-c", "java ${JAVA_OPTIONS} -jar /geekjoke-0.0.1-SNAPSHOT.jar"]