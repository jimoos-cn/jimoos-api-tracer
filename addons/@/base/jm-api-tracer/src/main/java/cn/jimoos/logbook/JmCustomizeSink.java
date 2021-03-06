package cn.jimoos.logbook;

import cn.jimoos.dao.ApiTraceMapper;
import cn.jimoos.model.ApiTrace;
import cn.jimoos.util.UaUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zalando.logbook.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 写入 request 和 response 的中间件
 *
 * @author :keepcleargas
 * @date :2021-01-26 10:37.
 */
@AllArgsConstructor
@Slf4j
public class JmCustomizeSink implements Sink {
    private static final transient String USER_AGENT = "user-agent";
    private static final transient String X_TENANT_ID = "X-Tenant-Id";
    private static final transient String LOGGING_TRACE = "/traces";
    private static final transient String UNKNOWN = "unKnown";

    private final ApiTraceMapper apiTraceMapper;
    private final HttpLogFormatter formatter;

    @Override
    public void write(Precorrelation precorrelation, HttpRequest request) throws IOException {
        log.debug("request: {},{}", precorrelation, formatter.format(precorrelation, request));
    }

    @Override
    public void write(Correlation correlation, HttpRequest request, HttpResponse response) throws IOException {
        log.debug("request: {},{}", correlation, formatter.format(correlation, response));

        if (request.getRequestUri().contains(LOGGING_TRACE)) {
            //跳过自己
            return;
        }
        ApiTrace loggingTrace = new ApiTrace();

        loggingTrace.setRequest(formatter.format(correlation, request));
        loggingTrace.setResponse(formatter.format(correlation, response));

        HttpHeaders httpHeaders = request.getHeaders();
        List<String> tenantIds = httpHeaders.get(X_TENANT_ID);
        if (!CollectionUtils.isEmpty(tenantIds)) {
            loggingTrace.setTenantId(tenantIds.get(0));
        }

        if (!CollectionUtils.isEmpty(httpHeaders.get(USER_AGENT))) {
            loggingTrace.setUserAgent(httpHeaders.get(USER_AGENT).get(0));
        }
        loggingTrace.setUri(request.getRequestUri());
        int port = request.getPort().orElse(0);
        loggingTrace.setDomain(String.format("%s:%s", request.getHost(), port == 0 ? "" : String.valueOf(port)));
        loggingTrace.setPath(request.getPath());
        loggingTrace.setIp(getIpAddress(request));
        loggingTrace.setCorrelation(correlation.getId());
        loggingTrace.setOsFamily(UaUtil.getInstance().getOsFamily(loggingTrace.getUserAgent()));
        loggingTrace.setMethod(request.getMethod());
        //毫秒
        loggingTrace.setDuration((int) (correlation.getDuration().toMillis()));
        loggingTrace.setStatus(String.valueOf(response.getStatus()));
        loggingTrace.setCreateAt(System.currentTimeMillis());

        apiTraceMapper.insert(loggingTrace);
    }

    private String getIpAddress(HttpRequest request) {
        HttpHeaders httpHeaders = request.getHeaders();
        String ip = null;
        try {
            // 获取用户真是的地址
            List<String> xIps = httpHeaders.getOrDefault("X-Real-IP", new ArrayList<>());
            // 获取多次代理后的IP字符串值
            List<String> xFors = httpHeaders.getOrDefault("X-Forwarded-For", new ArrayList<>());
            if (!CollectionUtils.isEmpty(xFors) && !UNKNOWN.equalsIgnoreCase(xFors.get(0))) {
                String xFor = xFors.get(0);
                // 多次反向代理后会有多个IP值，第一个用户真实的IP地址
                int index = xFor.indexOf(",");
                if (index >= 0) {
                    return xFor.substring(0, index);
                } else {
                    return xFor;
                }
            }
            if (!CollectionUtils.isEmpty(xIps)) {
                ip = xIps.get(0);
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = getFirstElement(httpHeaders, "Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = getFirstElement(httpHeaders, "WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = getFirstElement(httpHeaders, "HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = getFirstElement(httpHeaders, "HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemote();
            }
        } catch (Exception e) {
            log.error("Exception:", e);
        }
        return ip;
    }

    private String getFirstElement(HttpHeaders httpHeaders, String key) {
        List<String> elements = httpHeaders.getOrDefault(key, new ArrayList<>());
        return CollectionUtils.isEmpty(elements) ? null : elements.get(0);
    }

}
