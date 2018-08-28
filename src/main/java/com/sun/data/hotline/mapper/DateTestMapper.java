package com.sun.data.hotline.mapper;

import com.sun.data.hotline.domain.vo.DateDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author sunjian.
 */
@Mapper
public interface DateTestMapper {
    // 测试时间戳
    @Select("insert into date_test values (#{id},#{createTime},#{updateTime})")
    int save(@Param("id") String id, @Param("createTime")LocalDateTime createTime, @Param("updateTime")LocalDateTime updateTime);
}
