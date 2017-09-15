package com.scottfu.sflibrary.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fujindong on 2017/3/6.
 */

public class DateFormatter {

    public String ZhihuDailyDateFormat(long date){

        String sDate;
        Date d = new Date(date + 24 * 60 * 60 * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);
        return sDate;
    }


    public String DoubanDateFormat(long date){
        String sDate;
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        sDate = format.format(d);
        return sDate;
    }

    public long getData(long time,long duration) {
        long reservationTime = time + duration*60*1000;
        long timecurrentTimeMillis = System.currentTimeMillis();

        long countDown = reservationTime - timecurrentTimeMillis;
//        long time1 = timecurrentTimeMillis - time;
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//        String time2 = sdf.format(time1);
        return countDown;

    }

}
