package com.sun.data.service;

import com.sun.data.LocalDataProcessApplication;
import com.sun.data.hotline.domain.UserAuthority;
import com.sun.data.hotline.service.UserAuthorityMove;
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
public class UserAuthorityMoveTest
{
    @Autowired
    private UserAuthorityMove authorityMove;
    @Test
    public void moveAuth() throws Exception
    {
        System.out.println("begin-----------------");
        authorityMove.moveAuth();
        System.out.println("end-------------------");
    }
}
