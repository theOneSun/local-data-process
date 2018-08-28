package com.sun.data.hotline.service;

import com.sun.data.hotline.domain.AreaDictionaries;
import com.sun.data.hotline.domain.UserPermission;
import com.sun.data.hotline.mapper.AreaDictionaryMapper;
import com.sun.data.hotline.mapper.CityMapper;
import com.sun.data.hotline.mapper.UserPermissionMapper;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户迁移
 *
 * @author sunjian.
 */
@Service
public class UserMove
{
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private AreaDictionaryMapper areaMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    public void moveUser()
    {
        /*
        2.更新城市
        3.匹配城市id
        1.查询全部用户的权限
        4.新增userAuthority
         */
        /*int i = cityMapper.updateUserPermissionCity();
        System.out.println("userPermission更新了" + i + "条数据");

        //匹配城市id
        // (1)查询用户购买过的所有的城市
        // (2)查询城市id,封装成map,特殊处理吉林和石河子两个重名的城市
        // (3)更新权限表中的cityId
        List<UserPermission> userPermissionList = userPermissionMapper.listAllHasPermission();
        List<String> cityList = new ArrayList<>();
        userPermissionList.forEach(userPermission -> cityList.add(userPermission.getCity()));
        List<AreaDictionaries> areaDictionaryList = areaMapper.listAllByAreaName(cityList);
        Map<String, String> cityMap = new HashMap<>();//key是城市,value是id
        areaDictionaryList.forEach(area -> cityMap.put(area.getAreaName(), area.getId()));
        cityMap.put("吉林", "644");
        cityMap.put("石河子", "2656");
        userPermissionList.forEach(userPermission -> userPermission.setCity);*/

    }
}
