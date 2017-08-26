package com.yeapao.brake.serialport;

/**
 * Created by fujindong on 2017/8/26.
 */

public interface OutputAccountListener {

    void printAccount(String Account);

    void onFail();

}
