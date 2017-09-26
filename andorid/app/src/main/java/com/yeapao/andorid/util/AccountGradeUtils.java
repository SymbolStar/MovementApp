package com.yeapao.andorid.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.circle.circledetail.CircleDetailMessageAdapter;

/**
 * Created by fujindong on 2017/9/25.
 */

public class AccountGradeUtils {

    public static Drawable getGradeDrawable(Context mContext, int grade) {
        switch (grade) {
            case 1:
                 return mContext.getResources().getDrawable(R.drawable.level1);
            case 2:
                return mContext.getResources().getDrawable(R.drawable.level2);
            case 3:
                return mContext.getResources().getDrawable(R.drawable.level3);
            case 4:
                return mContext.getResources().getDrawable(R.drawable.level4);
            case 5:
                return mContext.getResources().getDrawable(R.drawable.level5);
            case 6:
                return mContext.getResources().getDrawable(R.drawable.level6);
            case 7:
                return mContext.getResources().getDrawable(R.drawable.level7);
            case 8:
                return mContext.getResources().getDrawable(R.drawable.level8);
            case 9:
                return mContext.getResources().getDrawable(R.drawable.level9);
            case 10:
                return mContext.getResources().getDrawable(R.drawable.level10);
            case 11:
                return mContext.getResources().getDrawable(R.drawable.level12);
            case 12:
                return mContext.getResources().getDrawable(R.drawable.level13);
            case 13:
                return mContext.getResources().getDrawable(R.drawable.level14);
            case 14:
                return mContext.getResources().getDrawable(R.drawable.level15);
            case 15:
                return mContext.getResources().getDrawable(R.drawable.level1);
            default:
                return mContext.getResources().getDrawable(R.drawable.level1);
        }
    }
}
