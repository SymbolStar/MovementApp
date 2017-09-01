package com.scottfu.sflibrary.permission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2017/7/10.
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static Activity getTopActivity() {
        if (activityList.isEmpty()) {
            return null;
        } else {
//            返回栈底的activity
            return activityList.get(activityList.size() - 1);
        }
    }
}
