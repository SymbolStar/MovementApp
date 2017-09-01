package com.scottfu.sflibrary.popwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.scottfu.sflibrary.R;

import java.lang.reflect.Method;

/**
 * Created by fujindong on 2017/7/14.
 */


public class PopWindowCategory extends PopupWindow {

    private GridView gridView;
    private Context mContext;

    private CategoryAdapter gridAdapter;
    PopItemClick onLeftClick;
    PopItemClick onRightClick;

    public void loadData(int type, String[] items){
        if (gridAdapter == null) {
            gridAdapter = new CategoryAdapter(mContext, items);
            gridView.setAdapter(gridAdapter);
        } else {
            gridAdapter.setmItems(items);
            gridAdapter.notifyDataSetChanged();
        }
        gridAdapter.setListener(new CategoryAdapter.OnTextClickListener() {

            @Override
            public void onClick(View view, int position) {
                // TODO Auto-generated method stub
                gridAdapter.showSelectItem(position);
                onLeftClick.onItemClick(position);
            }
        });
    }

    public void initPop(Context context, final PopItemClick onLeftClick, final PopItemClick onRightClick){
        mContext = context;
        this.onLeftClick = onLeftClick;
        this.onRightClick = onRightClick;
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_category, null);
        gridView = (GridView)view.findViewById(R.id.grid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                gridAdapter.showSelectItem(arg2);
                onLeftClick.onItemClick(arg2);
            }
        });

        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(false);  //这里一定要设置为false，否则点击事件无法下传
//		this.setBackgroundDrawable(new ColorDrawable(0x000000)); //不能设置背景，
        this.setOutsideTouchable(true);
//		view.setOnTouchListener(new OnTouchListener() {
//
//				public boolean onTouch(View v, MotionEvent event) {
//
//					Log.e("zll", "touch cancel");
//					if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
//						dismiss();
//						return true;
//					} else {
//						v.performClick();
//						return true;
//					}
//				}
//			});
        setPopupWindowTouchModal(this, false);
    }

    public interface PopItemClick{
        public void onItemClick(int position);
    }

    public void setPopupWindowTouchModal(PopupWindow popupWindow,
                                         boolean touchModal) {
        if (null == popupWindow) {
            return;
        }
        Method method;
        try {

            method = PopupWindow.class.getDeclaredMethod("setTouchModal",
                    boolean.class);
            method.setAccessible(true);
            method.invoke(popupWindow, touchModal);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
