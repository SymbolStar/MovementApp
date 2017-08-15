package com.yeapao.andorid.util;

/**
 * Created by fujindong on 2017/8/16.
 * 疼痛等级选择器接口
 */

public interface PickerPainListener {
    void getPainValue(String value);

    void cancel();

    void determine();
}
