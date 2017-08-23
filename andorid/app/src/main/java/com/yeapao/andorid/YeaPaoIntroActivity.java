package com.yeapao.andorid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.util.SampleSlide;

/**
 * Created by fujindong on 2017/8/13.
 */

public class YeaPaoIntroActivity extends AppIntro {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (GlobalDataYepao.getUser(this) == null) {
            addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout1));
            addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout2));
            addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout3));
//        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout4));
        } else {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }


    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }


}
