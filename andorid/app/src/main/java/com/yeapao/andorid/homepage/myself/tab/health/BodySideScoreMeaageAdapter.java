package com.yeapao.andorid.homepage.myself.tab.health;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.HealthDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/8/11.
 */

public class BodySideScoreMeaageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "BodySideScoreMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private List<HealthDataModel.DataBean.TestScoresListOutsBean> testScoresListOutsBeanList;

    public BodySideScoreMeaageAdapter(Context context,
                                      List<HealthDataModel.DataBean.TestScoresListOutsBean>
                                              scoresListOutsBeen
    ) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        testScoresListOutsBeanList = scoresListOutsBeen;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScoreViewHolder(inflater.inflate(R.layout.item_health_score, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ScoreViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return testScoresListOutsBeanList.size();
    }

     class ScoreViewHolder  extends RecyclerView.ViewHolder {
        @BindView(R.id.rc_health_score)
        RadarChart healthRadarChart;
        @BindView(R.id.tv_health_data)
        TextView tvHealthData;
        @BindView(R.id.tv_sum_score)
        TextView tvSumScore;

        ScoreViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            initView();
        }

        private void initView() {

            healthRadarChart.getDescription().setEnabled(false);

            healthRadarChart.setWebLineWidth(1f);
            healthRadarChart.setWebColor(mContext.getResources().getColor(R.color.text_hint_color));
            healthRadarChart.setWebLineWidthInner(1f);
            healthRadarChart.setWebColorInner(mContext.getResources().getColor(R.color.text_hint_color));
            healthRadarChart.setWebAlpha(100);

//            setData();

            healthRadarChart.getDescription().setEnabled(false);
            Legend l = healthRadarChart.getLegend();
            l.setEnabled(false);

            healthRadarChart.animateXY(
                    1400, 1400,
                    Easing.EasingOption.EaseInOutQuad,
                    Easing.EasingOption.EaseInOutQuad);

            XAxis xAxis = healthRadarChart.getXAxis();
//        xAxis.setTypeface(mTfLight);
            xAxis.setTextSize(14f);
            xAxis.setYOffset(0f);
            xAxis.setXOffset(0f);
            xAxis.setValueFormatter(new IAxisValueFormatter() {

                private String[] mActivities = new String[]{"下肢爆发力", "体前驱", "下肢力量", "躯干力量", "体成分"};

                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return mActivities[(int) value % mActivities.length];
                }
            });
            xAxis.setTextColor(mContext.getResources().getColor(R.color.text_hint_color));//设置四周点字的颜色

            YAxis yAxis = healthRadarChart.getYAxis();
//        yAxis.setTypeface(mTfLight); 设置字体
            yAxis.setLabelCount(5, false);
            yAxis.setTextSize(9f);
            yAxis.setAxisMinimum(0f);
            yAxis.setAxisMaximum(80f);
            yAxis.setDrawLabels(false);

        }


         public void setData(int position) {

             float mult = 80;
             float min = 20;
             int cnt = 5;

             ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
             ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

             // NOTE: The order of the entries when being added to the entries array determines their position around the center of
             // the chart.
//             for (int i = 0; i < cnt; i++) {
//                 float val1 = (float) (Math.random() * mult) + min;
//                 LogUtil.e(TAG,String.valueOf(val1));
//                 entries1.add(new RadarEntry(val1));
//
//                 float val2 = (float) (Math.random() * mult) + min;
//                 entries2.add(new RadarEntry(val2));
//             }
             LogUtil.e(TAG,String.valueOf(position));


            entries1.add(new RadarEntry(testScoresListOutsBeanList.get(position).getLowerExtremityStrength()));
            entries1.add(new RadarEntry(testScoresListOutsBeanList.get(position).getPrecursor()));
            entries1.add(new RadarEntry(testScoresListOutsBeanList.get(position).getUpperLimbStrength()));
            entries1.add(new RadarEntry(testScoresListOutsBeanList.get(position).getLegEndurance()));
            entries1.add(new RadarEntry(  Float.valueOf(testScoresListOutsBeanList.get(position).getInBody())));



             RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
//        set1.setColor(Color.rgb(103, 110, 129));
//        set1.setFillColor(Color.rgb(103, 110, 129));
             set1.setColor(mContext.getResources().getColor(R.color.colorPrimary));
             set1.setFillColor(mContext.getResources().getColor(R.color.colorPrimary));
             set1.setDrawFilled(true);
             set1.setFillAlpha(180);
             set1.setLineWidth(2f);
             set1.setDrawHighlightCircleEnabled(true);
             set1.setDrawHighlightIndicators(false);

//             RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
//             set2.setColor(Color.rgb(121, 162, 175));
//             set2.setFillColor(Color.rgb(121, 162, 175));
//             set2.setDrawFilled(true);
//             set2.setFillAlpha(180);
//             set2.setLineWidth(2f);
//             set2.setDrawHighlightCircleEnabled(true);
//             set2.setDrawHighlightIndicators(false);

             ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
             sets.add(set1);
//        sets.add(set2);

             RadarData data = new RadarData(sets);
//        data.setValueTypeface(mTfLight);
             data.setValueTextSize(8f);
             data.setDrawValues(false);
             data.setValueTextColor(Color.BLACK);

             healthRadarChart.setData(data);
             healthRadarChart.invalidate();
         }



    }
}
