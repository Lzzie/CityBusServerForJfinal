package com.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单工具类
 */
public class OrderUtils {

    public static String getOrderNum(){
        String orderNo = "";
        String date = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        String rendomStr = getRandomString(3);
        orderNo = date+""+rendomStr;
        return orderNo;
    }

    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }
    public static int getMoney() {
        int m=2;
        int[] money={10,5,5,2,2,2,2,2,2,2};
        Random random = new Random();
        int range = money.length;
        m=random.nextInt(range);
        return money[m];
    }
    public static void main(String[] args) {
       System.out.println(getOrderNum());
    }

}
