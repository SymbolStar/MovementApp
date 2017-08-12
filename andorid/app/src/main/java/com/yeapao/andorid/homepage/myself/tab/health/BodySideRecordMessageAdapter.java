package com.yeapao.andorid.homepage.myself.tab.health;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.HealthDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/8/11.
 */

public class BodySideRecordMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "BodySideRecordMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private List<HealthDataModel.DataBean.TestTecordListOutsBean> testTecordListOutsBeanList;

    private OnRecyclerViewClickListener mListener;

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public BodySideRecordMessageAdapter(Context context,
                                        List<HealthDataModel.DataBean.TestTecordListOutsBean>
                                                testTecordListOutsBeen) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        testTecordListOutsBeanList = testTecordListOutsBeen;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ;
        return new ViewHolder(inflater.inflate(R.layout.item_tab_body_detail, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String[] date = testTecordListOutsBeanList.get(position).getStartDate().split(" ");
        String[] day = date[0].split("-");
        String mouths, days;
        if (day[1].substring(0, 1).equals("0")) {
            mouths = day[1].substring(1, 2);
        } else {
            mouths = day[1];
        }

        if (day[2].substring(0, 1).equals("0")) {
            days = day[2].substring(1, 2);
        } else {
            days = day[2];
        }
        ((ViewHolder) holder).tvTabTitle.setText(mouths + "月" + days + "日");

    }

    @Override
    public int getItemCount() {
        return testTecordListOutsBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.tv_tab_title)
        TextView tvTabTitle;
        @BindView(R.id.rl_health_record_tab)
        RelativeLayout rlHealthRecordTab;

        ViewHolder(View view,OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }
}
