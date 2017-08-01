package com.yeapao.andorid.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yeapao.andorid.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/8/1.
 */

public class ChooseFoodMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ChooseFoodMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private List<Integer> mlist = new ArrayList<>();


    public ChooseFoodMessageAdapter(Context context, List<Integer> list) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mlist = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.dialog_card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        RoundImageView ivAvatar;
        @BindView(R.id.iv_dislike)
        ImageView ivDislike;
        @BindView(R.id.iv_like)
        ImageView ivLike;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
