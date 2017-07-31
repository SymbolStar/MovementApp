package com.scottfu.sflibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by fujindong on 2017/7/31.
 */

public class SystemDateUtil {

    public static String getCurrentYYYYMMDD() {
        String date =null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         date=sdf.format(new java.util.Date());
        return date;
    }


    public static String[] getCurrentWeek() {
        String[] weekDays = new String[7];
        String[] weeks = new String[7];
        try {
            weeks = WeekToDay.getStringDate(getCurrentYYYYMMDD());
            for (int i = 0; i < weeks.length; i++) {
                String[] a = weeks[i].split("-");
                weekDays[i] = a[2];
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekDays;
    }






}
