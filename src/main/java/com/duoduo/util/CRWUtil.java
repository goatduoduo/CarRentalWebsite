package com.duoduo.util;

import java.math.BigDecimal;

public class CRWUtil {
    //存放没有特定意义的UTIL类
    public static BigDecimal getFirst(String s){
        //1,10000
        String[] temp=s.split(",");
        return BigDecimal.valueOf(Double.valueOf(temp[0]));
    }
    public static BigDecimal getSecond(String s){
        //1,10000
        String[] temp=s.split(",");
        return BigDecimal.valueOf(Double.valueOf(temp[1]));
    }
    public static double scale(double num,int scale){
        BigDecimal b = new BigDecimal(num);
        return b.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
