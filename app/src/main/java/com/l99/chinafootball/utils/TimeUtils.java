package com.l99.chinafootball.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lifeix-101 on 2016/7/14.
 */
public class TimeUtils {
    public static  String getDate(long timestamp){

        Date date = new Date(timestamp);
        String week=getWeek(date);
        String time = new java.text.SimpleDateFormat("MM月dd日").format(date);
        return time+" "+week;
 }
    public static String getWeek(Date date){
        String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }
    public static  String getHour(long timestamp){

        Date date = new Date(timestamp);
        String time = new java.text.SimpleDateFormat("mm:ss").format(date);

        return time;
    }

    public static  String getDateAndTime(long timestamp){

        Date date = new Date(timestamp);
        String time = new java.text.SimpleDateFormat("yyyy-MM-dd  hh:mm").format(date);
        return time;
    }

    public static String getBirthday(long timestamp) {
        Date date = new Date(timestamp);
        String time = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
        return time;
    }

    public static int[] getSystemTime(){

        int times[] = new int[3];
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        times[0]=year;
        times[1]=month+1;
        times[2]=day;
        return times;
    }
}
