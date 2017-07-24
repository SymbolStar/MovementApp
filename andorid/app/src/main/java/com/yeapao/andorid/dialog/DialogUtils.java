package com.yeapao.andorid.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yeapao.andorid.R;

import org.w3c.dom.Text;

import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/20.
 */

public class DialogUtils {

    public static void showDialog(final Context context, String title, String hint, String item1, String item2, final DialogCallback listener){
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();

        final TextView onLine;
        final TextView down;

        Button cancel;
        Button sure;

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_lesson_view, null);
        if(title != null){
            ((TextView)v.findViewById(R.id.tv_title)).setText(title);
        }
        if (hint != null) {
            ((TextView) v.findViewById(R.id.tv_dialog_hint)).setText(hint);
        }

            down = (TextView) v.findViewById(R.id.tv_item_1);
            onLine = (TextView) v.findViewById(R.id.tv_item_2);

        down.setText(item1);
        onLine.setText(item2);
            onLine.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    listener.onItemClick(1);
                    onLine.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape));
                    down.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape_n));
                }
            });

        down.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    listener.onItemClick(2);
                    down.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape));
                    onLine.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape_n));
                }
            });


        cancel = (Button) v.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLeftClick();
                dialog.dismiss();
            }
        });
        sure = (Button) v.findViewById(R.id.btn_sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setContentView(v);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);
    }
}