--liquibase formatted sql

--changeset kison:1

create table t_logging_trace
(
    id          bigint unsigned auto_increment
        primary key,
    request     text          null comment '请求',
    response    longtext      null comment '返回值',
    tenant_id   varchar(128)  null comment '租户ID',
    uri         varchar(1024) null comment 'path',
    domain      varchar(1023) null comment '域名',
    path        varchar(255)  null comment '路径',
    ip          varchar(255)  null comment '请求IP',
    user_agent  varchar(255)  null comment 'user-agent',
    correlation varchar(128)  null comment 'correlation',
    status      varchar(50)   null comment '返回状态',
    duration    int           null comment '请求时间',
    create_at   bigint        null comment '创建事件'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    comment 'api 接口的 request 和 response 跟踪';

