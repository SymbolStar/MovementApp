package com.yeapao.andorid.util;

import android.graphics.LinearGradient;

import java.text.SimpleDateFormat;

/**
 * Created by fujindong on 2017/9/27.
 */

public class CircleDateUtils {
    public static String getCircleDate(long time) {
        String date="";
        long currentTime = System.currentTimeMillis();
        long time1 = currentTime - time;

        long ss = time1 / 1000;
        if (ss < 60) {
            date = "刚刚";
            return date;
        }

        long mm = ss / 60;
        if (mm < 60) {
            date = String.valueOf(mm) + "分钟前";
            return date;
        }

        long hh = mm / 60;
        if (hh < 24) {
            date = String.valueOf(hh) + "小时前";
            return date;
        }

        long dd = hh / 24;
        if (dd <= 7) {
            date = String.valueOf(dd) + "天前";
            return date;
        }

        if (dd < 365) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            date = sdf.format(time);
            return date;
        }

        if (dd > 365) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.format(time);
            return date;
        }
        return date;
    }


    public static String getCircleMessageDate(long time) {
        String date = "";
        long currentTime = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String date1 = sdf.format(time);

        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
        String date2 = sdf1.format(currentTime);

        if (date1.equals(date2)) {
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            date = sdf2.format(time);
            return date;
        } else {
            SimpleDateFormat sdf3 = new SimpleDateFormat("MM-dd HH:mm");
            date = sdf3.format(time);
            return date;
        }
    }

}
