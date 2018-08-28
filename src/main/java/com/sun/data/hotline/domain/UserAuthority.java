package com.sun.data.hotline.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户权限关联
 *
 * @author sunjian.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthority
{
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 城市id
     */
    private String cityId;
    /**
     * 城市
     */
    private String city;
//    /**
//     * 权限id
//     */
//    private String authorityId;
    /**
     * 型号id
     */
    private String productTypeId;
}
