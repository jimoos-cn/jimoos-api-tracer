# jimoos-api-tracer 服务端代码

![Build Status](https://github.com/jimoos-cn/jimoos-api-tracer/workflows/Java%20CI%20with%20Maven/badge.svg)

实现 `Spring-boot mvc` 的接口 `request/response` 的捕捉应用。

## 项目构建

`mvn clean package -Dmaven.test.skip=true`

## 快速上手

## 项目入口

jm-api-tracer-manager Api跟踪日志管理入口

## 项目插件列表

- `addons/@`下 为项目内插件
- `addons/depends`为外部插件，外部插件禁止修改。

## 发布组件到oss

```bash
 export GPG_TTY=$(tty) 
 mvn clean deploy -P release 
```

## Docker 镜像构建

```bash
 #构建镜像
 docker build -t keepcleargas/jm-api-tracer-manager -f docker/Dockerfile .
 #推送镜像
 docker push keepcleargas/jm-api-tracer-manager
```