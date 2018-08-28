package com.sun.data.hotline.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author sunjian.
 */
@Mapper
public interface CityMapper
{
    //更新hotline_score的上半年数据的城市名称
    int updateCityFirstHalf();

    //更新userPermission的城市
    int updateUserPermissionCity();
}
