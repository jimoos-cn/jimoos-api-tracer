package cn.jimoos.service;

import cn.jimoos.form.be.ApiTraceQueryForm;
import cn.jimoos.model.ApiTrace;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ApiTraceVo;

/**
 * @author :keepcleargas
 * @date :2021-01-26 15:48.
 */
public interface ApiTraceService {
    /**
     * 查询 跟踪日志
     *
     * @param loggingTraceQueryForm 查询表单
     * @return Page<LoggingTraceVo>
     */
    Page<ApiTraceVo> query(ApiTraceQueryForm loggingTraceQueryForm);

    /**
     * 获取 详细的日志 request 和 response
     *
     * @param id LoggingTraceId
     * @return 返回详细 包含request 和 response 对象
     */
    ApiTrace getOne(Long id);
}
