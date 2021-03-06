package cn.jimoos.vo;

import cn.jimoos.model.ApiTrace;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author :keepcleargas
 * @date :2021-01-26 15:44.
 */
@Data
@Slf4j
public class ApiTraceVo {
    private Long id;

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

    private String method;
    private String osFamily;

    public static ApiTraceVo from(ApiTrace loggingTrace) {
        ApiTraceVo loggingTraceVo = new ApiTraceVo();
        BeanUtils.copyProperties(loggingTrace, loggingTraceVo);
        return loggingTraceVo;
    }
}
