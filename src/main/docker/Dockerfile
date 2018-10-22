FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD SpringBootDemo-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
# 启动
# ubuntu@VM-248-241-ubuntu:~/app/java$ docker run --name javademo1 -p 10001:10001 -v $PWD/:/spring/ -d java1
