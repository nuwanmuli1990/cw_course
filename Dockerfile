FROM java:8
WORKDIR /
RUN echo "Asia/Colombo" | tee /etc/timezone
RUN dpkg-reconfigure --frontend noninteractive tzdata
ADD course-0.0.1-SNAPSHOT.jar course-0.0.1-SNAPSHOT.jar
EXPOSE 5070
ENTRYPOINT ["java","-jar","course-0.0.1-SNAPSHOT.jar", "--server.servlet.context-path=/cs", "--server.port=5080"]
