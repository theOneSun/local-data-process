package com.sun.data.hotline.mapper;

import com.sun.data.hotline.domain.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sunjian.
 */
@Mapper
public interface UserAuthorityMapper
{
    // 批量添加权限
    int batchInsert(@Param("insertList") List<UserAuthority> insertList);

    //查询用户购买的型号的模块类型
    Map<String,String> getCategoryTypeByUserAndYearAndCityId(@Param("userId") String userId, @Param("cityIdList") List<String> cityIdList, @Param("year") Integer year, @Param("month") String month);
}
