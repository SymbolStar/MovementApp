package com.yeapao.andorid.homepage.shopping;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.ShoppingDataModel;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/15.
 */

public class ShoppingMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener onRecyclerViewClickListener;
    private ShoppingDataModel shoppingDataModel;




    public ShoppingMessageAdapter(Context context, ShoppingDataModel shoppingDataModel) {

        mContext = context;
        inflater = LayoutInflater.from(context);
        this.shoppingDataModel = shoppingDataModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_shop_card, parent, false), onRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (shoppingDataModel.getData().get(position).isLineChecked()) {
            ((ViewHolder) holder).cbDown.setChecked(true);
        } else {
            ((ViewHolder) holder).cbDown.setChecked(false);
        }


        ((ViewHolder) holder).tvPrice.setText(String.valueOf("￥"+shoppingDataModel.getData().get(position).getLinePrice()));
        ((ViewHolder) holder).tvShoppingTitle.setText(shoppingDataModel.getData().get(position).getCurriculumName());

//        ((ViewHolder) holder).cbDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (((ViewHolder) holder).cbDown.isChecked()) {
//
//                LogUtil.e("++++","++++");
//
//                }
//            }
//        });

    }



    @Override
    public int getItemCount() {
        return shoppingDataModel.getData().size();
    }

    public void setItemClickListener(OnRecyclerViewClickListener listener) {
        onRecyclerViewClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_lesson)
        ImageView ivLesson;
        @BindView(R.id.cb_down)
        CheckBox cbDown;
        @BindView(R.id.tv_shopping_title)
        TextView tvShoppingTitle;
        @BindView(R.id.tv_price)
        TextView tvPrice;


        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            initView();
        }

        private void initView() {
            cbDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(v, getLayoutPosition());

                }
            });
        }

    }
}
