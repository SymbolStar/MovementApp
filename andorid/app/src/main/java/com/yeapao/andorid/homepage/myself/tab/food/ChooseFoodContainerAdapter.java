package com.yeapao.andorid.homepage.myself.tab.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.FoodInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2017/8/1.
 */

public class ChooseFoodContainerAdapter extends BaseAdapter {


    private static final String TAG = "ChooseFoodContainerAdapter";


    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mImageArrayList = new ArrayList<>();
    private List<FoodInfoModel.DataBean.LunchBean> foodList;

    public ChooseFoodContainerAdapter(Context context, List<FoodInfoModel.DataBean.LunchBean> foodInfoModel) {

        mContext = context;
        mInflater = LayoutInflater.from(context);
        foodList = foodInfoModel;
    }

    @Override
    public int getCount() {

        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
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
            viewHolder.foodNum = (TextView) convertView.findViewById(R.id.tv_food_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(mContext, foodList.get(position).getImage(),
                R.drawable.food1,viewHolder.foodImage);
        viewHolder.foodNameTextView.setText(foodList.get(position).getName());
        viewHolder.foodNum.setText(foodList.get(position).getMeasure());
//        viewHolder.foodImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.food1));
//        viewHolder.foodNameTextView.setText("包子");
        return convertView;
    }

    class ViewHolder {
        ImageView foodImage;
        TextView foodNameTextView;
        TextView foodNum;

    }


}
