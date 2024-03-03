FROM openjdk:17
ARG JAR_FILE=build/libs/apitest-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ./apitest-0.0.1-SNAPSHOT.jar
ENV TZ=Asia/Seoul
#컨테이너 실행될떄 수행될 명령어 지정
ENTRYPOINT ["java", "-jar", "./apitest-0.0.1-SNAPSHOT.jar"]
