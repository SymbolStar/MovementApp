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

    private int chooseBefore = 300;
    private int choosePo = 300;
    private boolean dowm = false;
    private boolean online = false;
    private boolean refresh = false;

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

        if (position == chooseBefore) {

            if (refresh) {
                ((ViewHolder) holder).cbDown.setChecked(false);
                ((ViewHolder) holder).cbOnline.setChecked(false);
                refresh = false;

            }
        }


        ((ViewHolder) holder).tvDownPrice.setText(String.valueOf(shoppingDataModel.getData().get(position).getLinePrice()));
        ((ViewHolder) holder).tvOnlinePrice.setText(String.valueOf(shoppingDataModel.getData().get(position).getOnlinePrice()));
        ((ViewHolder) holder).tvShoppingTitle.setText(shoppingDataModel.getData().get(position).getCurriculumName());

//        ((ViewHolder) holder).cbDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    if (shoppingDataModel.isAllChecked()) {
//                        refreshCheckBox();
//                        choosePo = position;
//                        shoppingDataModel.setAllChecked(true);
//                    } else {
//                        choosePo = position;
//                        shoppingDataModel.setAllChecked(true);
//                    }
//
//
//                } else {
//                    if (shoppingDataModel.isAllChecked()) {
//                        shoppingDataModel.setAllChecked(false);
//
//                    }
//
//
//                }
//            }
//        });
//
//        ((ViewHolder) holder).cbOnline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    if (shoppingDataModel.isAllChecked()) {
//                        refreshCheckBox();
//                        choosePo = position;
//                        shoppingDataModel.setAllChecked(true);
//                    } else {
//                        choosePo = position;
//                        shoppingDataModel.setAllChecked(true);
//                    }
//
//
//                } else {
//                    if (shoppingDataModel.isAllChecked()) {
//                        shoppingDataModel.setAllChecked(false);
//
//                    }
//                }
//            }
//        });


        ((ViewHolder) holder).cbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ViewHolder) holder).cbDown.isChecked()) {
                    if (chooseBefore == 300) {
                        chooseBefore = position;
                    } else {
                        refreshCheckBox();
                        chooseBefore = position;

                    }


                }
            }
        });

        ((ViewHolder) holder).cbOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ViewHolder) holder).cbOnline.isChecked()) {
                    if (chooseBefore == 300) {
                        chooseBefore = position;
                    } else {
                        refreshCheckBox();
                        chooseBefore = position;

                    }
                }
            }
        });


    }

    private void refreshCheckBox() {
        refresh = true;
        if (chooseBefore == 300) {

        } else {
            notifyItemChanged(chooseBefore);
        }

    }

    @Override
    public int getItemCount() {
        return shoppingDataModel.getData().size();
    }

    public void setItemClickListener(OnRecyclerViewClickListener listener) {
        onRecyclerViewClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_lesson)
        ImageView ivLesson;
        @BindView(R.id.tv_online_price)
        TextView tvOnlinePrice;
        @BindView(R.id.tv_down_price)
        TextView tvDownPrice;
        @BindView(R.id.cb_online)
        CheckBox cbOnline;
        @BindView(R.id.cb_down)
        CheckBox cbDown;
        @BindView(R.id.tv_shopping_title)
        TextView tvShoppingTitle;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }
}
