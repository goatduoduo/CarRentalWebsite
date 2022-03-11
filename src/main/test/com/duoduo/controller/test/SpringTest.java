package com.duoduo.controller.test;

import com.duoduo.controller.duoduoTestController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void testSpring(){
        ApplicationContext applicationContext=
                new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取SpringTest类
        duoduoTestController springTest=(duoduoTestController) applicationContext.getBean("duoduoTestController");
        springTest.hello();
    }
}
