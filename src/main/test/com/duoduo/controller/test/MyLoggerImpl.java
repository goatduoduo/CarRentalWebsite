package com.duoduo.controller.test;

import java.lang.reflect.Method;

public class MyLoggerImpl implements MyLogger{

    @Override
    public void saveIntoMethodTime(Method method) {
        System.out.println("进入"+method.getName()+"方法的时间为："+ System.currentTimeMillis());
    }

    @Override
    public void saveOutMethodTime(Method method) {
        System.out.println("离开"+method.getName()+"方法的时间为："+ System.currentTimeMillis());
    }
}
