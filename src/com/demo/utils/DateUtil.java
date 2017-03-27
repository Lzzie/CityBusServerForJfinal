package com.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by Administrator on 2016/7/18.
 */
public class DateUtil {

    public static final long SECOND_TIME = 1000;

    public static final long MINUTE_TIME = 60*SECOND_TIME;

    public static final long HOUR_TIME = 60*MINUTE_TIME;

    public static final long DAY_TIME = 24*HOUR_TIME;

    public static final long MONTH_TIME = 30*DAY_TIME;

    public static final DateFormat df_second = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final DateFormat df_hour = new SimpleDateFormat("yyyy-MM-dd HH");

    public static final DateFormat df_day = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate2Str(Date date, DateFormat df) {
        return df.format(date);
    }

    public static Date formatStr2Date(String dateStr, DateFormat df) {
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }

    public static int getTimeLevel(int consumeTime){
        int level=1;
        if(consumeTime<=60){
            level=1;
        }
        if(60<consumeTime&&consumeTime<=120){
            level=2;
        }
        if(120<consumeTime&&consumeTime<=180){
            level=3;
        }
        if(180<consumeTime&&consumeTime<=240){
            level=4;
        }
        return level;
    }


}
