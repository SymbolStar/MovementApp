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
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2017/8/15.
 */

public class DatePickerDialog extends DialogFragment {


    private TextView mCancel;
    private TextView mDetermine;
    private WheelDatePicker mWeelPicker;
    private List<String> pickerList = new ArrayList<>();


    private DateSelectedListener mListener;


    public interface DateSelectedListener {
        void getDate(String Date);

        void cancel();

    }




    public void setPickerListener(DateSelectedListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_date_picker);
        dialog.setCanceledOnTouchOutside(false);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);



        mCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        mDetermine = (TextView) dialog.findViewById(R.id.tv_determine);
        mWeelPicker = (WheelDatePicker) dialog.findViewById(R.id.pain_wheel_center);
        mWeelPicker.setYearStart(1900);
        mWeelPicker.setYearEnd(2017);
        mWeelPicker.setItemTextColor(getResources().getColor(R.color.text_color));
        mWeelPicker.setSelectedItemTextColor(getResources().getColor(R.color.colorPrimary));
        mWeelPicker.setIndicatorColor(getResources().getColor(R.color.text_color));

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                sb.append(mWeelPicker.getCurrentYear());
                sb.append("-");
                sb.append(mWeelPicker.getCurrentMonth());
                sb.append("-");
                sb.append(mWeelPicker.getCurrentDay());

              LogUtil.e("yyy",String.valueOf( mWeelPicker.getCurrentYear()));
              LogUtil.e("ddd",String.valueOf( mWeelPicker.getCurrentDay()));
                LogUtil.e("mmm",String.valueOf(mWeelPicker.getCurrentMonth()));
                mListener.getDate(sb.toString());
            }
        });
        return dialog;

    }
}
