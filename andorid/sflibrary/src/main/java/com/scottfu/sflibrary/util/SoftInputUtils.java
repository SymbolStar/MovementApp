package com.scottfu.sflibrary.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by fujindong on 2017/9/23.
 */

public class SoftInputUtils {

    public static void hideSoftinput(Activity activity) {
        //隐藏软键盘
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
