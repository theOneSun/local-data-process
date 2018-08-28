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
public class Ranking
{
    /*
     `id` varchar(32) NOT NULL,
      `city_id` varchar(32) DEFAULT NULL,
      `city` varchar(100) DEFAULT NULL,
      `category_id` varchar(32) DEFAULT NULL COMMENT '榜单类别',
      `rank` int(11) DEFAULT NULL COMMENT '排名',
      `rank_change` varchar(10) NOT NULL DEFAULT '' COMMENT '排名变化',
      `year` int(4) DEFAULT NULL COMMENT '年份',
      `half_year` varchar(10) DEFAULT NULL,
     */
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
     * 榜单分类id
     */
    private String categoryId;
    /**
     * 排名
     */
    private Integer rank;
    /**
     * 排名变化
     */
    private String rankChange;
    /**
     * 年
     */
    private int year;
    /**
     * 半年
     */
    private String halfYear;
}
