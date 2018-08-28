package com.sun.data.hotline.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateDemo {
    private String id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
