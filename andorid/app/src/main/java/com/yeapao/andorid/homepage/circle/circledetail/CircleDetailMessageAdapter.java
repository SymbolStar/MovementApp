package com.yeapao.andorid.homepage.circle.circledetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LayoutInflater inflater;
    private CircleDetailContract.View mView;

    public CircleDetailMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
