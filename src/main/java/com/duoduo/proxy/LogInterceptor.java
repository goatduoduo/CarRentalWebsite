package com.duoduo.proxy;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor {

//    @Before(value = "execution(* com.duoduo.controller.*.*(..))")
//    public void before(){
//        System.out.println("进入方法的时间为"+System.currentTimeMillis());
//    }
//
//    @After(value = "execution(* com.duoduo.controller.*.*(..))")
//    public void after(){
//        System.out.println("退出方法的时间为"+System.currentTimeMillis());
//    }

}
