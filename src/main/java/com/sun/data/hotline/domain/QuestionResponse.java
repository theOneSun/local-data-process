package com.sun.data.hotline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 问题回应
 *
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse
{
    private String id;
    private String city;

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
