package com.yeapao.andorid.homepage.myself.tab.food;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.ToastManager;
import com.scottfu.sflibrary.view.HeightGirdView;
import com.yeapao.andorid.R;
import com.yeapao.andorid.dialog.ChooseFoodDialogFragment;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.lesson.LessonFragmentView;
import com.yeapao.andorid.model.CookListDetailModel;
import com.yeapao.andorid.model.FoodInfoModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/27.
 */

public class FoodMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private FragmentManager fragmentManager;
    private FoodInfoModel foodInfoModel;


    public FoodMessageAdapter(Context context, FragmentManager fragmentManager, FoodInfoModel foodInfoModel) {

        mContext = context;
        inflater = LayoutInflater.from(context);
        this.fragmentManager = fragmentManager;
        this.foodInfoModel = foodInfoModel;
    }

    private static final int MYSELF_FOOD_WEEK = 0;
    private static final int MYSELF_FOOD_MENU = 1;
    private static final int MYSEKF_FOOD_HINT = 2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MYSELF_FOOD_MENU:
                return new MenuViewHolder(inflater.inflate(R.layout.item_food_menu, parent, false));
            case MYSEKF_FOOD_HINT:
                return new HintViewHolder(inflater.inflate(R.layout.item_food_hint, parent, false));
        }
        return null;
    }


    public void refresh() {
        CookListDetailModel.DataBean dataBean = GlobalDataYepao.getFoodDetail();
        if (dataBean.getMeal().equals("breakfast")) {
            FoodInfoModel.DataBean.LunchBean breakfastBean = new FoodInfoModel.DataBean.LunchBean();
            breakfastBean.setImage(dataBean.getImage());
            breakfastBean.setMeasure(dataBean.getMeasure());
            breakfastBean.setName(dataBean.getName());
            foodInfoModel.getData().getBreakfast().set(Integer.valueOf(dataBean.getPosition())-1, breakfastBean);
            notifyDataSetChanged();
        } else if (dataBean.getMeal().equals("lunch")) {
            FoodInfoModel.DataBean.LunchBean breakfastBean = new FoodInfoModel.DataBean.LunchBean();
            breakfastBean.setImage(dataBean.getImage());
            breakfastBean.setMeasure(dataBean.getMeasure());
            breakfastBean.setName(dataBean.getName());
            foodInfoModel.getData().getLunch().set(Integer.valueOf(dataBean.getPosition())-1, breakfastBean);
            notifyDataSetChanged();
        } else {
            FoodInfoModel.DataBean.LunchBean breakfastBean = new FoodInfoModel.DataBean.LunchBean();
            breakfastBean.setImage(dataBean.getImage());
            breakfastBean.setMeasure(dataBean.getMeasure());
            breakfastBean.setName(dataBean.getName());
            foodInfoModel.getData().getDinner().set(Integer.valueOf(dataBean.getPosition())-1, breakfastBean);
            notifyDataSetChanged();

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MenuViewHolder) {
            if (position == 0) {
                ((MenuViewHolder) holder).tvFoodMenu.setText("早餐");
                ChooseFoodContainerAdapter foodAdapter = new ChooseFoodContainerAdapter(mContext,foodInfoModel.getData().getBreakfast());
                ((MenuViewHolder) holder).gvFoodList.setAdapter(foodAdapter);
                ((MenuViewHolder) holder).gvFoodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToastManager.showToast(mContext,"OnClick");
//                    DialogUtils.showCardSwipe(mContext);
//                    ChooseFoodDialogFragment dialogFragment = new ChooseFoodDialogFragment();
//                    dialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
//                    dialogFragment.show(fragmentManager,"dialog");
                        TestActivity.start(mContext,"breakfast",String.valueOf(position+1));
                    }
                });

            } else if (position == 1) {
                ((MenuViewHolder) holder).tvFoodMenu.setText("午餐");
                ChooseFoodContainerAdapter foodAdapter = new ChooseFoodContainerAdapter(mContext,foodInfoModel.getData().getLunch());
                ((MenuViewHolder) holder).gvFoodList.setAdapter(foodAdapter);
                ((MenuViewHolder) holder).gvFoodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToastManager.showToast(mContext,"OnClick");
                        TestActivity.start(mContext,"lunch",String.valueOf(position+1));
                    }
                });

            } else {
                ((MenuViewHolder) holder).tvFoodMenu.setText("晚餐");
                ChooseFoodContainerAdapter foodAdapter = new ChooseFoodContainerAdapter(mContext,foodInfoModel.getData().getDinner());
                ((MenuViewHolder) holder).gvFoodList.setAdapter(foodAdapter);
                ((MenuViewHolder) holder).gvFoodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToastManager.showToast(mContext,"OnClick");
                        TestActivity.start(mContext,"dinner",String.valueOf(position+1));
                    }
                });

            }
        }


    }

    @Override
    public int getItemViewType(int position) {

    if (position == 3) {
            return MYSEKF_FOOD_HINT;
        }
        return MYSELF_FOOD_MENU;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_food_menu)
        TextView tvFoodMenu;
        @BindView(R.id.gv_food_list)
        HeightGirdView gvFoodList;


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
