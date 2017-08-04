package com.yeapao.andorid.homepage.myself.tab.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;

import java.util.ArrayList;

/**
 * Created by fujindong on 2017/8/1.
 */

public class ChooseFoodContainerAdapter extends BaseAdapter {


    private static final String TAG = "ChooseFoodContainerAdapter";


    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mImageArrayList = new ArrayList<>();


    public ChooseFoodContainerAdapter(Context context ) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        for (int i = 0; i < 4; i++) { // TODO test
            mImageArrayList.add("food");
        }
    }

    @Override
    public int getCount() {
        return mImageArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_choose_food, null);
            viewHolder = new ViewHolder();
            viewHolder.foodImage = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.foodNameTextView = (TextView) convertView.findViewById(R.id.tv_food_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.foodImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.food1));
        viewHolder.foodNameTextView.setText("包子");


        return convertView;
    }

    class ViewHolder {
        ImageView foodImage;
        TextView foodNameTextView;

    }


}
