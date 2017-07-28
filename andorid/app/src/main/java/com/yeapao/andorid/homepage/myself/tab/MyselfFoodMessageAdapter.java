package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfFoodMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;


    public MyselfFoodMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    private static final int MYSELF_FOOD_WEEK = 0;
    private static final int MYSELF_FOOD_MENU = 1;
    private static final int MYSEKF_FOOD_HINT = 2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MYSELF_FOOD_WEEK:
                return new WeekViewHolder(inflater.inflate(R.layout.item_food_week, parent, false));
            case MYSELF_FOOD_MENU:
                return new MenuViewHolder(inflater.inflate(R.layout.item_food_menu, parent, false));
            case MYSEKF_FOOD_HINT:
                return new HintViewHolder(inflater.inflate(R.layout.item_food_hint, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return MYSELF_FOOD_WEEK;
        } else if (position == 4) {
            return MYSEKF_FOOD_HINT;
        }
        return MYSELF_FOOD_MENU;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class WeekViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tue)
        TextView tvTue;
        @BindView(R.id.tv_sat)
        TextView tvSat;
        @BindView(R.id.tv_fri)
        TextView tvFri;
        @BindView(R.id.tv_thu)
        TextView tvThu;
        @BindView(R.id.tv_wed)
        TextView tvWed;
        @BindView(R.id.tv_mon)
        TextView tvMon;
        @BindView(R.id.tv_sun)
        TextView tvSun;

        WeekViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_food_menu)
        TextView tvFoodMenu;
        @BindView(R.id.gv_food_list)
        GridView gvFoodList;

        MenuViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class HintViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_cooking)
        TextView tvCooking;
        @BindView(R.id.tv_seasoning)
        TextView tvSeasoning;

        HintViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
