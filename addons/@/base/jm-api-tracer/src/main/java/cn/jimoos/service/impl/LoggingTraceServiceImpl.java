package cn.jimoos.service.impl;

import cn.jimoos.dao.ApiTraceMapper;
import cn.jimoos.form.be.ApiTraceQueryForm;
import cn.jimoos.model.ApiTrace;
import cn.jimoos.service.ApiTraceService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.ApiTraceVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-01-26 15:29.
 */
@Service
public class LoggingTraceServiceImpl implements ApiTraceService {
    @Resource
    ApiTraceMapper apiTraceMapper;

    @Override
    public Page<ApiTraceVo> query(ApiTraceQueryForm loggingTraceQueryForm) {
        Long count = apiTraceMapper.queryTableCount(loggingTraceQueryForm.toQm());

        if (count == 0) {
            return Page.empty();
        }
        List<ApiTrace> list = apiTraceMapper.queryTable(loggingTraceQueryForm.toQm());
        return Page.create(count, list.stream().map(ApiTraceVo::from).collect(Collectors.toList()));
    }

    @Override
    public ApiTrace getOne(Long id) {
        return apiTraceMapper.selectByPrimaryKey(id);
    }
}
