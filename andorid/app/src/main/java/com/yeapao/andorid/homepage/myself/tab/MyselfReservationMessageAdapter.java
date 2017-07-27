package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.ReservationListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

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
    private ReservationListModel listModel;

    public MyselfReservationMessageAdapter(Context context, ReservationListModel model) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        listModel = model;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_myself_reservation, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvReservationTitle.setText(listModel.getData().get(position).getCurriculumType());
        ((ViewHolder) holder).tvReservationDate.setText(listModel.getData().get(position).getDate());
        ((ViewHolder) holder).tvReservationTime.setText(listModel.getData().get(position).getTime());
        if (listModel.getData().get(position).getStatus().equals("1")) {
            ((ViewHolder) holder).ivStatusOff.setVisibility(View.GONE);
        } else {
            ((ViewHolder) holder).ivStatusOff.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listModel.getData().size();
    }


     class ViewHolder extends RecyclerView.ViewHolder{
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
        @BindView(R.id.iv_status_off)
        ImageView ivStatusOff;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.v_qrcode)
        void qrcodeClick(View v) {
            LogUtil.e(TAG,"qrcodeClick");
            DialogUtils.showQRCode(mContext,"1112323211", GlobalDataYepao.getUser(mContext));
        }

        @OnClick(R.id.tv_reservation_delete)
        void reservationDelete(View v) {
            LogUtil.e(TAG,"reservationDelete");
        }

    }
}
