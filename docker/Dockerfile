FROM ccr.ccs.tencentyun.com/xiaohuodui/openjdk

MAINTAINER qisheng.chen@jimoos.cn

ADD addons/@/apps/jm-api-tracer-manager/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]