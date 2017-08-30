package com.yeapao.brake.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.brake.R;
import com.yeapao.brake.bean.ScreenModel;

import java.util.zip.Inflater;

/**
 * Created by fujindong on 2017/8/29.
 */

public class MouthRankMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ScreenModel screenModel;

    private static final int TOP = 1;
    private static final int ITEM = 2;

    private int type = 0;


    /**
     *
     * @param context
     * @param screenModel
     * @param type  月1／周2
     */
    public MouthRankMessageAdapter(Context context, ScreenModel screenModel,int type) {
        mContext = context;
        this.screenModel = screenModel;
        inflater = LayoutInflater.from(context);
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TOP:
                return new TopHolder(inflater.inflate(R.layout.mouth_top, parent, false));
            case ITEM:
                return new ItemHolder(inflater.inflate(R.layout.accoumt_item, parent, false));
        }

        return null;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP;
        } else {
            return ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopHolder) {
            if (type == 1) {
                ((TopHolder) holder).titleTextView.setText("本月入场排行榜");
            } else {
                ((TopHolder) holder).titleTextView.setText("本周私教排行榜");
            }
        } else {

            if (position == 1) {
                ((ItemHolder)holder).levelTextView.setBackground(mContext.getResources().getDrawable(R.drawable.one));
                ((ItemHolder) holder).levelTextView.setText("");
            } else if (position == 2) {
                ((ItemHolder)holder).levelTextView.setBackground(mContext.getResources().getDrawable(R.drawable.two));
                ((ItemHolder) holder).levelTextView.setText("");
            } else if (position == 3) {
                ((ItemHolder) holder).levelTextView.setBackground(mContext.getResources().getDrawable(R.drawable.three));
                ((ItemHolder) holder).levelTextView.setText("");
            } else {
                ((ItemHolder) holder).levelTextView.setText(String.valueOf(position));
            }

            if (type == 1) {
                ((ItemHolder) holder).accountTextView.setText(screenModel.getMonthRank().get(position-1).getUser_name());
                ((ItemHolder) holder).countTextView.setText(String.valueOf(screenModel.getMonthRank().get(position-1).getCount()));
            } else {
                ((ItemHolder) holder).accountTextView.setText(screenModel.getWeekRank().get(position-1).getUser_name());
                ((ItemHolder) holder).countTextView.setText(String.valueOf(screenModel.getWeekRank().get(position-1).getCount()));
            }
        }

    }

    @Override
    public int getItemCount() {
        return screenModel.getMonthRank().size()+1;
    }


    class TopHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;

        public TopHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            titleTextView = (TextView) view.findViewById(R.id.tv_rank_name);
        }
    }


    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView levelTextView;
        private TextView accountTextView;
        private TextView countTextView;

        public ItemHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            levelTextView = (TextView) view.findViewById(R.id.tv_level);
            accountTextView = (TextView) view.findViewById(R.id.tv_name);
            countTextView = (TextView) view.findViewById(R.id.tv_count);
        }

    }


}
