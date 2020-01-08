FROM openjdk:8-jdk-alpine

VOLUME /tmp

ENV LANG en_US.UTF-8

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN echo 'Asia/Shanghai' >/etc/timezone

ADD mycloud-gateway.jar app.jar

# Image创建容器时的初始化内存，最大内存，及启动时使用的profile. -c为清除以前启动的数据
# "-Xms1024m","-Xmx1024m"

EXPOSE 8443

ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","/app.jar","-c"]
