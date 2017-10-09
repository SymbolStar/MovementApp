package com.yeapao.andorid.util;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

/**
 * Created by fujindong on 2017/9/27.
 */

public class SpannableTextUtils {

    public static SpannableStringBuilder setTextTwoColor(String startStr,String endStr) {

        StringBuffer sb = new StringBuffer();
        String content = startStr + endStr;
        sb.append(content);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(sb.toString());
        int start = 0;
        int end = startStr.length();
        int color = Color.rgb(128, 117, 151);
        stringBuilder.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        start = end;
        end = start + endStr.length();
        int color2 = Color.rgb(55, 52, 62);
        stringBuilder.setSpan(new ForegroundColorSpan(color2), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        return stringBuilder;
    }
}
