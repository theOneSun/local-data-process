package com.sun.data.hotline.mapper;

import com.sun.data.hotline.domain.AreaDictionaries;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface AreaDictionaryMapper
{
    //根据城市名称查询全部的
    List<AreaDictionaries> listAllByAreaName(List<String> cityList);
}
