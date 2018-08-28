package com.sun.data.hotline.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunjian.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotlineScore
{
    private String id;

    /**
     * 城市id
     */
    private String cityId;

    /**
     * 城市
     */
    private String city;

    /**
     * 年份
     */
    private int year;

    /**
     * 上下半年
     */
    private String halfYear;
    /**
     * 100指标个数
     */
    private int hundredIndexCount;
    /**
     * 100指标个数排名
     */
    private int hundredIndexCountRank;

    /**
     * 转接人工时间得分
     */
    private Double throughConnectionScore;
    /**
     * 综服务水平得分(不包含接通率)
     */
    private Double serviceLevelScore;

    /**
     * 服务水平排名
     */
    private Integer serviceLevelRank;

    /**
     * 服务水平较上期排名变化
     */
    private String serviceLevelChange;

    /**
     * 综合不含接通率得分(不包含接通率)
     */
    private Double noRateScore;

    /**
     * 综合不含接通率排名(不包含接通率)
     */
    private Integer noRateRank;

    /**
     * 综合不含接通率排名变化(不包含接通率)
     */
    private String noRateChange;
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
     * 总体得分(含接通率)
     */
    private Double comprehensiveScore;

    /**
     * 综合得分排名(含接通率)
     */
    private Integer comprehensiveRank;

    /**
     * 综合得分排名较上期排名变化
     */
    private String comprehensiveChange;

    /**
     * 接通率
     */
    private Double connectionRate;
    /**
     * 接通率排名
     */
    private int connectionRateRank;
    /**
     * 接通率排名变化
     */
    private String connectionRateChange;

    //----------服务礼仪
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

    //--------沟通技巧
    /**
     * 不随意插话,耐心倾听
     */
    private Double patience;

    /**
     * 主动询问问题细节
     */
    private Double askDetail;

    /**
     * 表达清晰流畅、有条理'
     */
    private Double expressionClear;

    //----------问题回应
    /**
     * 态度不生硬
     */
    private Double attitude;

    /**
     * 不推诿
     */
    private Double noShuffle;

    /**
     * 快速找准问题
     */
    private Double fastFindProblem;

    /**
     * 清楚说明处理流程
     */
    private Double explainClearProcess;

    /**
     * 清楚解释政策法规
     */
    private Double explainClearPolicy;
}
