package cn.jimoos.dao;

import cn.jimoos.model.ApiTrace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-01-27 14:31.
 */

@Mapper
public interface ApiTraceMapper {
    int insert(ApiTrace record);

    ApiTrace selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ApiTrace record);

    int batchInsert(@Param("list") List<ApiTrace> list);


    /**
     * 查询 LoggingTrace 列表
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${status}等参数 的 倒序分页查询
     * @return List<ApiTrace>
     */
    List<ApiTrace> queryTable(Map qm);

    /**
     * 查询 LoggingTrace 总数
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${status}等参数 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map qm);
}