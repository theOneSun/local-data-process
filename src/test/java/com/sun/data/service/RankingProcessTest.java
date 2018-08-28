package com.sun.data.service;

import com.sun.data.LocalDataProcessApplication;
import com.sun.data.hotline.service.RankingProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author sunjian.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocalDataProcessApplication.class)
public class RankingProcessTest
{
    @Autowired
    private RankingProcess rankingProcess;

    // 礼仪榜,技巧榜,回应榜变化封装
    @Test
    public void threeListChange() throws Exception {
        rankingProcess.setPoliteChange();
        rankingProcess.setSkillChange();
        rankingProcess.setResponseChange();
    }

    @Test
    public void insertConnectionRateRanking() throws Exception {
        rankingProcess.insertConnectionRateRanking();
    }
}
