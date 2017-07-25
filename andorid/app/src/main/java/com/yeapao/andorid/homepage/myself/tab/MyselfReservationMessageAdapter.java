package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/25.
 */

public class MyselfReservationMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = "MyselfReservationMessageAdapter";
    private Context mContext;
    private LayoutInflater inflater;

    public MyselfReservationMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_myself_reservation, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_reservation_time)
        TextView tvReservationTime;
        @BindView(R.id.tv_reservation_location)
        TextView tvReservationLocation;
        @BindView(R.id.tv_reservation_date)
        TextView tvReservationDate;
        @BindView(R.id.tv_reservation_title)
        TextView tvReservationTitle;
        @BindView(R.id.tv_reservation_delete)
        TextView tvReservationDelete;
        @BindView(R.id.v_qrcode)
        View vQrcode;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.v_qrcode)
        void qrcodeClick(View v) {
            LogUtil.e(TAG,"qrcodeClick");
        }

        @OnClick(R.id.tv_reservation_delete)
        void reservationDelete(View v) {
            LogUtil.e(TAG,"reservationDelete");
        }

    }
}
