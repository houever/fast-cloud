FROM openjdk:8-jdk-alpine

MAINTAINER  houqj

VOLUME /tmp

ENV LANG en_US.UTF-8

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN echo 'Asia/Shanghai' >/etc/timezone

ADD fast-auth.jar app.jar
#    -Xms64m #JVM启动时的初始堆大小
#    -Xmx128m #最大堆大小
#    -Xmn64m #年轻代的大小，其余的空间是老年代
#    -XX:MaxMetaspaceSize=128m #元数据区大小
#    -XX:CompressedClassSpaceSize=64m #使用 -XX：CompressedClassSpaceSize 设置为压缩类空间保留的最大内存。
#    -Xss256k #线程空间大小
#    -XX:InitialCodeCacheSize=4m #
#    -XX:ReservedCodeCacheSize=8m # 这是由 JIT（即时）编译器编译为本地代码的本机代码（如JNI）或 Java 方法的空间
#    -XX:MaxDirectMemorySize=16m
#    -XX:NativeMemoryTracking=summary #开启内存追踪
#    -jar app.jar
# 查看java运用pid
#jps -l
## 查看堆/方法区/线程等内存占用（必需启动内存追踪）
#jcmd 2923 VM.native_memory
## 查看jvm默认值 Xmx Xms GC等
#java -XX:+PrintFlagsFinal -version
#java -XX:+PrintCommandLineFlags -version
## 查看gc次数/时间
#jstat -gcutil  15004  / jstat -gc  15004
## 查看线程栈信息
#jstack -l pid
## 可视化查看内存/线程的信息
#jvisualvm / jconsole
## 导出dump文件
#jmap -dump:format=b,file=heapdump.hprof  pid
#使用jvisualvm可打开hprof文件
# Image创建容器时的初始化内存，最大内存，及启动时使用的profile. -c为清除以前启动的数据
# "-Xms1024m","-Xmx1024m"
#-XX:PermSize=256m -XX:MaxPermSize=512m -XX:MaxNewSize=512m
ENTRYPOINT ["java","-Xms256m","-Xmx512m","-XX:PermSize=256m","-XX:MaxPermSize=512m","-XX:MaxNewSize=512m","-jar","-Djava.security.egd=file:/dev/./urandom","-javaagent:/agent/skywalking-agent.jar","-Dskywalking.agent.service_name=${APP_NAME}","-Dskywalking.collector.backend_service=10.1.70.84:11800","/app.jar","-c"]
