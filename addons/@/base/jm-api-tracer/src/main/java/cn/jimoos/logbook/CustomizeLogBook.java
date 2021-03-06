package cn.jimoos.logbook;

import cn.jimoos.dao.ApiTraceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Sink;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-01-26 09:54.
 */
@Component
@Slf4j
public class CustomizeLogBook {
    @Resource
    ApiTraceMapper apiTraceMapper;

    @Bean
    public Sink sink(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") final HttpLogFormatter formatter) {
        return new JmCustomizeSink(apiTraceMapper, formatter);
    }
}
