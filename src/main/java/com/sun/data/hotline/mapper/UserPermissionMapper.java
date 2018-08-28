package com.sun.data.hotline.mapper;

import com.sun.data.hotline.domain.AreaDictionaries;
import com.sun.data.hotline.domain.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface UserPermissionMapper
{
    //查询所有有的用户权限
    List<UserPermission> listAllHasPermission();

    //根据userPermission的城市查询对应的城市id
    List<AreaDictionaries> listUserPermissionCity();

    //更新城市id
    int updatePermissionCityId(@Param("permissionList") List<UserPermission> permissionList);
}
