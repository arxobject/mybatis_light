package com.ninjutsu.test;

import com.ninjutsu.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author:arxobject
 * @desc : test
 * @create time:8/19
 */

@Slf4j
public class UnitTest {

    @Test
    public void Test1(){
        User usr = new User();
        usr.setUser_name("kaka");
        log.info(usr.toString());
    }
}
