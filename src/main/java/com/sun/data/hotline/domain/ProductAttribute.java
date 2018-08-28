package com.sun.data.hotline.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 商品属性
 *
 * @author zhangpeng
 */
@Setter
@Getter
public class ProductAttribute
{
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 名称的唯一性标识
     */
    private String value;

    private String productId;
    private String productTypeId;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 城市所属省份ID
     */
    private String cityParentId;

    private boolean available;
    /**
     * 是否购买
     */
    private boolean purchased;
    /**
     * 截止日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
