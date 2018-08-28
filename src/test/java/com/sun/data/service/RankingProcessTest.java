package com.sun.data.service;

import com.sun.data.LocalDataProcessApplication;
import com.sun.data.hotline.domain.HalfYear;
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
        /*
        不能插,用excel排名导入数据
         */
//        rankingProcess.insertData(2018,HalfYear.second_half);
        rankingProcess.setPoliteChange();
        rankingProcess.setSkillChange();
        rankingProcess.setResponseChange();
    }

    @Test
    //接通率榜单
    public void insertConnectionRateRanking() throws Exception {
        rankingProcess.insertConnectionRateRanking(2018, HalfYear.second_half);
    }

    /*@Test
    //飙升榜 不能用
    public void insertIncreaseRanking() throws Exception {
        rankingProcess.setIncrease(2018, HalfYear.second_half);
    }*/
}
