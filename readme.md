# jimoos-api-tracer 服务端代码

![Build Status](https://github.com/jimoos-cn/jimoos-api-tracer/workflows/Java%20CI%20with%20Maven/badge.svg)

实现 `Spring-boot mvc` 的接口 `request/response` 的捕捉应用。

## 项目构建

`mvn clean package -Dmaven.test.skip=true`

## 项目入口

api-starter C端接口入口 portal-starter 后台接口入口

## 项目插件列表

- `addons/@`下 为项目内插件
- `addons/depends`为外部插件，外部插件禁止修改。
