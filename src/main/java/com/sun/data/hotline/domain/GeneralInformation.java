package com.sun.data.hotline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInformation
{
    private String id;
    private String city;
    /**
     * 100指标的个数
     */
    private Integer hundredIndexCount;
    /**
     * 100分指标个数排名
     */
    private Integer hundredIndexCountRank;
    /**
     * 服务综合排名
     */
    private Integer serviceRanking;
    /**
     * 综合服务得分(不包含接通率)
     */
    private Double scoreExcludeConnectRate;
    /**
     * 接通率
     */
    private Double rate;
    /**
     * 接通率排名
     */
    private Integer rateRanking;
    /**
     * 转接人工时间得分
     */
    private Double throughConnectionScore;
    /**
     * 接待礼仪
     */
    private Double receptionEtiquette;
    /**
     * 沟通技巧
     */
    private Double communicationSkill;
    /**
     * 问题解决情况
     */
    private Double solveStatus;
    /**
     * 总体排名(含接通率)
     */
    private Integer rankIncludeConnectRate;
    /**
     * 总体得分(含接通率)
     */
    private Double scoreIncludeConnectRate;
}
