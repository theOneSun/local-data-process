package com.sun.data.hotline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接待礼仪详细数据
 *
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceptionPolite
{
    private String id;
    /**
     * 城市
     */
    private String city;
    /**
     * 礼貌问好、礼貌称呼对方
     */
    private Double greeting;
    /**
     * 自我介绍
     */
    private Double selfIntroduction;
    /**
     * 询问需求
     */
    private Double askDemand;
    /**
     * 使用普通话
     */
    private Double mandarin;
    /**
     * 语速适中，清晰报读
     */
    private Double speedDistinct;
    /**
     * 查询资料前后礼貌致歉
     */
    private Double querySorry;
    /**
     * 再次询问需求
     */
    private Double askDemandAgain;
    /**
     * 礼貌告别
     */
    private Double farewell;
    /**
     * 致谢
     */
    private Double thanks;
    /**
     * 寻求满意度评价
     */
    private Double questEvaluate;
}
