--liquibase formatted sql

--changeset kison:2

rename table t_logging_trace to JM_API_TRACE;

alter table JM_API_TRACE
    add method VARCHAR(50) null comment 'http method,eg. POST' after domain;

alter table JM_API_TRACE
    add os_family VARCHAR(50) null comment '系统' after user_agent;
