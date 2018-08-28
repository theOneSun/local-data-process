package com.sun.data.hotline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermission
{
    private String id;
    /**
     * 用户ID
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
    /**
     * 综合信息权限
     */
    private boolean generalInformation;
    /**
     * 接待礼仪指标权限
     */
    private boolean receptionIndex;
    /**
     * 沟通技巧指标权限
     */
    private boolean communicationIndex;
    /**
     * 问题回应指标权限
     */
    private boolean responseIndex;
}
