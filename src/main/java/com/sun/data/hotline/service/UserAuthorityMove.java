package com.sun.data.hotline.service;

import com.sun.data.hotline.domain.AreaDictionaries;
import com.sun.data.hotline.domain.UserAuthority;
import com.sun.data.hotline.domain.UserPermission;
import com.sun.data.hotline.mapper.CityMapper;
import com.sun.data.hotline.mapper.UserAuthorityMapper;
import com.sun.data.hotline.mapper.UserPermissionMapper;
import com.sun.data.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunjian.
 */
@Service
public class UserAuthorityMove
{
    private final UserPermissionMapper userPermissionMapper;
    private final CityMapper cityMapper;
    private final UserAuthorityMapper userAuthorityMapper;
    private Map<String, String> cityMap;

    @Autowired
    public UserAuthorityMove(UserPermissionMapper userPermissionMapper, CityMapper cityMapper,
                             UserAuthorityMapper userAuthorityMapper)
    {
        this.userPermissionMapper = userPermissionMapper;
        this.cityMapper = cityMapper;
        this.userAuthorityMapper = userAuthorityMapper;
    }

    /**
     * 迁移用户权限
     */
    public void moveAuth()
    {
        /*
        1.插叙原来所有的userPermission
        2.更新userPermission城市名称
        3.匹配城市id
        4.转换为auth
         */
        //查询所有权限
        List<UserPermission> userPermissionList = userPermissionMapper.listAllHasPermission();
        // 更新城市名称
        cityMapper.updateUserPermissionCity();
        // 查询所有城市的id
        List<AreaDictionaries> permissionAreaList = userPermissionMapper.listUserPermissionCity();
        Map<String, String> cityMap = new HashMap<>();
        permissionAreaList.forEach(area -> cityMap.put(area.getAreaName(), area.getId()));
        cityMap.put("吉林", "644");
        cityMap.put("石河子", "2656");
        this.cityMap = cityMap;
        userPermissionList.forEach(userPermission ->
                                   {
                                       String city = userPermission.getCity();
                                       userPermission.setCityId(cityMap.get(city));
                                   });
        //匹配城市id
        userPermissionMapper.updatePermissionCityId(userPermissionList);

        //将userPermissionList转换插入到userAuth
        List<UserAuthority> userAuthorityList = convertPermissionToAuth(userPermissionList);
        if (ObjectUtils.isEmpty(userAuthorityList))
        {
            System.out.println("原用户权限无数据");
        }
        int batchInsert = userAuthorityMapper.batchInsert(userAuthorityList);
        System.out.println("插入了" + batchInsert + "条记录");

    }

    //  将permission转成userAuthority
    private List<UserAuthority> convertPermissionToAuth(List<UserPermission> permissionList)
    {
        if (ObjectUtils.isEmpty(permissionList))
        {
            return null;
        }
        List<UserAuthority> authInsertList = new ArrayList<>();
        permissionList.forEach(userPermission ->
                               {
                                   if (userPermission.isGeneralInformation() & !userPermission.isCommunicationIndex() & !userPermission
                                           .isReceptionIndex() & !userPermission.isResponseIndex())
                                   {
                                       //对应仅拥有服务水平权限
                                       UserAuthority userAuth = new UserAuthority(CommonUtils.uuid(),
                                                                                  userPermission.getUserId(),
                                                                                  cityMap.get(userPermission.getCity()),
                                                                                  userPermission.getCity(),
                                                                                  "cc80aca5e922463b957d3e9a43058167");
                                       authInsertList.add(userAuth);
                                   } else if (userPermission.isGeneralInformation() & userPermission.isCommunicationIndex() & userPermission
                                           .isReceptionIndex() & userPermission.isResponseIndex())
                                   {
                                       //对应拥有全部权限
                                       UserAuthority userAuth = new UserAuthority(CommonUtils.uuid(),
                                                                                  userPermission.getUserId(),
                                                                                  cityMap.get(userPermission.getCity()),
                                                                                  userPermission.getCity(),
                                                                                  "f35cdde5177c4141a4566036f843402b");
                                       authInsertList.add(userAuth);
                                   } else
                                   {
                                       // 权限有单开的
                                       throw new RuntimeException("权限有异常");
                                   }
                               });
        return authInsertList;
    }
}
