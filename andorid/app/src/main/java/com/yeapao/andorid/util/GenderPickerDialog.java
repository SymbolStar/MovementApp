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

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2017/8/15.
 */

public class GenderPickerDialog extends DialogFragment {


    private TextView menTextView;
    private TextView womenTextView;
    private TextView cancelTextView;

    private GenderClickListener mListener;

    public   interface GenderClickListener {
        void isMen();

        void isWomen();

        void isCancel();
    }


    public void setGenderListener(GenderClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_gender_picker);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);




        menTextView = (TextView) dialog.findViewById(R.id.tv_men);
        womenTextView = (TextView) dialog.findViewById(R.id.tv_women);
        cancelTextView = (TextView) dialog.findViewById(R.id.tv_cancel_gender);

        menTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                womenTextView.setTextColor(getResources().getColor(R.color.text_color));
                womenTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_shape_n));
                menTextView.setTextColor(getResources().getColor(R.color.text_color));
                menTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_shape));
                mListener.isMen();
            }
        });
        womenTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menTextView.setTextColor(getResources().getColor(R.color.text_color));
                menTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_shape_n));
                womenTextView.setTextColor(getResources().getColor(R.color.text_color));
                womenTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_shape));
                mListener.isWomen();
            }
        });

        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.isCancel();
            }
        });

        return dialog;

    }
}
