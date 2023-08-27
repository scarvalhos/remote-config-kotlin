FROM gradle:7.6.1-jdk17 AS build
LABEL mantainer="samcarvalhos"
WORKDIR /code
COPY . /code
RUN gradle clean build -x test
RUN ls build/libs/

FROM openjdk:17-jdk-slim

COPY --from=build ["/code/build/libs/*.jar", "app.jar"]

CMD ["/app.jar"]
EXPOSE 8080 9090