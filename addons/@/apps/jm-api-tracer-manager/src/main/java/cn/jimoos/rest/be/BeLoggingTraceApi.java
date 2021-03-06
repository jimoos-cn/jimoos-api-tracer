package cn.jimoos.rest.be;

import cn.jimoos.form.be.ApiTraceQueryForm;
import cn.jimoos.model.ApiTrace;
import cn.jimoos.service.ApiTraceService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ApiTraceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-02-26 15:50.
 */
@RestController
@RequestMapping("/bAdmin/v1/traces")
@Slf4j
public class BeLoggingTraceApi {
    @Resource
    ApiTraceService apiTraceService;

    @GetMapping(produces = "application/json; charset=utf-8")
    public Page<ApiTraceVo> traceTable(@ModelAttribute ApiTraceQueryForm form) {
        return apiTraceService.query(form);
    }

    @GetMapping(value = "{id}", produces = "application/json; charset=utf-8")
    public ApiTrace traceTable(@PathVariable("id") Long id) {
        return apiTraceService.getOne(id);
    }
}
