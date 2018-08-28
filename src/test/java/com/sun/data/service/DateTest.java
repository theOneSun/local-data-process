package com.sun.data.service;

import com.sun.data.LocalDataProcessApplication;
import com.sun.data.hotline.domain.vo.DateDemo;
import com.sun.data.hotline.mapper.DateTestMapper;
import com.sun.data.util.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author sunjian.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocalDataProcessApplication.class)
public class DateTest {
    @Autowired
    private DateTestMapper mapper;

    @Test
    public void testSave() throws Exception {
        DateDemo dateDemo = new DateDemo(CommonUtils.uuid(), LocalDateTime.now(), LocalDateTime.now());

        mapper.save(dateDemo.getId(),dateDemo.getCreateTime(),dateDemo.getUpdateTime());
    }
}
