package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-01-26 15:25.
 */
@Data
public class ApiTraceQueryForm extends AbstractAdminPageForm4L {
    private Long startTime;
    private Long endTime;
    /**
     * http status
     */
    private Integer status;
    private String path;
    /**
     * 租户ID
     */
    private String tenantId;
    private String userAgent;
    /**
     * 请求的 返回速度 大于 毫秒 单位的请求
     */
    private Integer gtMilliSecond;

    public Map<String, String> toQm() {
        Map queryMap = new HashMap(9);
        queryMap.put("path", path);
        queryMap.put("status", status);
        queryMap.put("tenantId", tenantId);
        queryMap.put("gtMilliSecond", gtMilliSecond);
        queryMap.put("userAgent", userAgent);
        queryMap.put("startTime", startTime);
        queryMap.put("endTime", endTime);
        queryMap.put("offset", offset);
        queryMap.put("limit", limit);
        return queryMap;
    }
}
