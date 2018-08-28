package com.sun.data.hotline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 沟通技巧
 *
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationSkill
{
    private String id;
    private String city;

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
}
