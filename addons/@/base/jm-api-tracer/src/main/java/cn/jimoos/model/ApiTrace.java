package cn.jimoos.model;

import lombok.Data;

/**
 * api 接口的 request 和 response 跟踪
 *
 * @author :keepcleargas
 * @date :2021-01-27 14:31.
 */
@Data
public class ApiTrace {
    private Long id;

    /**
     * 请求
     */
    private String request;

    /**
     * 返回值
     */
    private String response;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * path
     */
    private String uri;

    /**
     * 域名
     */
    private String domain;

    /**
     * http method,eg. POST
     */
    private String method;

    /**
     * 路径
     */
    private String path;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * user-agent
     */
    private String userAgent;

    /**
     * 系统
     */
    private String osFamily;

    /**
     * correlation
     */
    private String correlation;

    /**
     * 返回状态
     */
    private String status;

    /**
     * 请求时间
     */
    private Integer duration;

    /**
     * 创建事件
     */
    private Long createAt;
}