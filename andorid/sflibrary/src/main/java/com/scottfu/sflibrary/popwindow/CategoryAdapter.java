package com.scottfu.sflibrary.popwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scottfu.sflibrary.R;

/**
 * Created by fujindong on 2017/7/14.
 */


public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mItems;
    private int selectIndex = -1;
    OnTextClickListener listener;

    public CategoryAdapter(Context context, String[] items){
        this.mContext = context;
        this.mItems = items;
    }

    public void showSelectItem(int index){
        selectIndex = index;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    public String[] getmItems() {
        return mItems;
    }

    public void setmItems(String[] mItems) {
        this.mItems = mItems;
    }

    @Override
    public String getItem(int position) {
        return mItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_category, null);
        }

        TextView text = (TextView) convertView.findViewById(R.id.tv_item_text);
        text.setText(mItems[position]);

//		if(position == selectIndex){
//			text.setBackgroundColor(0xffb0aca8);
//		}else{
//			text.setBackgroundResource(0xF4F4F4);
//		}
        text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                listener.onClick(arg0, position);
            }
        });

        return convertView;
    }

    public interface OnTextClickListener {
        public void onClick(View view, int position);
    }

    public OnTextClickListener getListener() {
        return listener;
    }

    public void setListener(OnTextClickListener listener) {
        this.listener = listener;
    }

}
