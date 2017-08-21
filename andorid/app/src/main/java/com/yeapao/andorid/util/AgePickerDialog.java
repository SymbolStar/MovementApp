package com.yeapao.andorid.util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.yeapao.andorid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2017/8/15.
 */

public class AgePickerDialog extends DialogFragment {


    private TextView mCancel;
    private TextView mDetermine;
    private WheelPicker mWeelPicker;
    private TextView title;
    private List<String> pickerList = new ArrayList<>();


    private PickerPainListener mListener;


    public void setPickerPainListener(PickerPainListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_pain_paicker);
        dialog.setCanceledOnTouchOutside(false);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);


        for (int i = 0; i <= 150; i++) {
            pickerList.add(String.valueOf(i));
        }

        title = (TextView) dialog.findViewById(R.id.tv_age_title);
        title.setText("年龄");
        mCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        mDetermine = (TextView) dialog.findViewById(R.id.tv_determine);
        mWeelPicker = (WheelPicker) dialog.findViewById(R.id.pain_wheel_center);

        mWeelPicker.setData(pickerList);
        mWeelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                mListener.getPainValue(String.valueOf(data));
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.determine();
            }
        });
        return dialog;

    }
}
