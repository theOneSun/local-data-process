package com.sun.data.service;

import com.sun.data.LocalDataProcessApplication;
import com.sun.data.hotline.service.DataProcessService;
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
public class DataProcessServiceTest
{
    @Autowired
    private DataProcessService service;

    //从旧的表中导入数据
    @Test
    public void importDataFromOld()
    {
        System.out.println("-----------begin--------------");
        int i = service.addHotlineScore();
        System.out.println("导入了" + i + "条");
        System.out.println("-----------end--------------");
    }

    // 更新hotlineScore的城市id
    @Test
    public void updateCityId() throws Exception
    {
        int i = service.matchCityId();
        System.out.println("匹配成功了" + i + "条数据");
    }

    // 更新hotlineScore的没有城市id的数据的城市id
    // 针对新增的数据
    @Test
    public void updateNewCityId() throws Exception
    {
        int i = service.matchNewCityId();
        System.out.println("匹配成功了" + i + "条数据");
    }

    // 计算并更新排名变化
    @Test
    public void updateRankChange() throws Exception
    {
        int i = service.wrapperRankChange();
        System.out.println("更新排名成功了" + i + "条数据");
    }
}
