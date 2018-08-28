package com.sun.data.service;

import com.sun.data.LocalDataProcessApplication;
import com.sun.data.hotline.service.LocalChangeData;
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
public class LocalChangeDataTest
{
    @Autowired
    private LocalChangeData service;

    @Test
    public void change() throws Exception
    {
        int i = service.updateAttributeValue();
        System.out.println("改变了"+i+"条");
    }
}
