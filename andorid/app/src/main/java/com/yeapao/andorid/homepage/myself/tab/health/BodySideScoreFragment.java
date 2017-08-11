package com.yeapao.andorid.homepage.myself.tab.health;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeapao.andorid.R;

/**
 * Created by fujindong on 2017/8/10.
 */

public class BodySideScoreFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score,container,false);

        return view;

    }
}

