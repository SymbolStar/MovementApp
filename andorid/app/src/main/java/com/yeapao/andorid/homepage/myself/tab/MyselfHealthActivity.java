package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/8/4.
 */

public class MyselfHealthActivity extends BaseActivity {

    private static final String TAG = "MyselfHealthActivity";
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.v_score)
    View vScore;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.v_record)
    View vRecord;

    private LineChart weightChart;
    private LineChart bmiChart;
    private RadarChart healthRadarChart;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfHealthActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ButterKnife.bind(this);
        initTopBar();
        weightChart = (LineChart) findViewById(R.id.lc_weight_chart);
        bmiChart = (LineChart) findViewById(R.id.lc_bmi_chart);
        healthRadarChart = (RadarChart) findViewById(R.id.rc_health_score);

//        RabarChart 参数设置
//        healthRadarChart.setBackgroundColor(Color.rgb(60, 65, 82));

        healthRadarChart.getDescription().setEnabled(false);

        healthRadarChart.setWebLineWidth(1f);
        healthRadarChart.setWebColor(getResources().getColor(R.color.text_hint_color));
        healthRadarChart.setWebLineWidthInner(1f);
        healthRadarChart.setWebColorInner(getResources().getColor(R.color.text_hint_color));
        healthRadarChart.setWebAlpha(100);

        setData();

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
        xAxis.setTextColor(getResources().getColor(R.color.text_hint_color));//设置四周点字的颜色

        YAxis yAxis = healthRadarChart.getYAxis();
//        yAxis.setTypeface(mTfLight); 设置字体
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);


//        表的title
//        Legend l = healthRadarChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
////        l.setTypeface(mTfLight);  设置字体
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(5f);
//        l.setTextColor(Color.BLACK);

//   TODO 折线图
//        折线图的title
        Description description = new Description();
        description.setText("最新记录50kg");
        description.setTextColor(getResources().getColor(R.color.text_color));
        description.setTextSize(ScreenUtil.dpToPx(getContext(), 5));
        description.setPosition(1000, 80);

//        获取数据
        LineData data = getData(30, 50);
        LineData data1 = getData(30, 20);
        setupChart(weightChart, data, description);
        setupChart(bmiChart, data1, description);

//        XAxis lineXAxis = weightChart.getXAxis(); //折线图中不需要轴的样式
//        lineXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        lineXAxis.setTextSize(9f);
//        lineXAxis.setTextColor(getResources().getColor(R.color.text_color));
//        lineXAxis.setEnabled(true);
//        lineXAxis.setDrawAxisLine(true);

        YAxis yAxis1 = weightChart.getAxisLeft();
        yAxis1.setTextSize(9f);
        yAxis1.setTextColor(getResources().getColor(R.color.text_color));




    }


    @Override
    protected void initTopBar() {
        initTitle("健康数据库");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void setupChart(LineChart chart, LineData data, Description description) {


        chart.setDescription(description);

//        ((LineDataSet) data.getDataSetByIndex(0)).setCircleColorHole(color);

        // no description text
        chart.getDescription().setEnabled(true);

        // mChart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

//        chart.setBackgroundColor(color);

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        chart.setViewPortOffsets(10, 0, 10, 0);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setSpaceTop(40);
        chart.getAxisLeft().setSpaceBottom(40);
        chart.getAxisRight().setEnabled(false);

        chart.getXAxis().setEnabled(false);

        // animate calls invalidate()...
        chart.animateX(2500);
    }

    private LineData getData(int count, float range) {

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(i, val));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);

        // create a data object with the datasets
        LineData data = new LineData(set1);

        return data;
    }


    public void setData() {

        float mult = 80;
        float min = 20;
        int cnt = 5;

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mult) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mult) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
//        set1.setColor(Color.rgb(103, 110, 129));
//        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setColor(getResources().getColor(R.color.colorPrimary));
        set1.setFillColor(getResources().getColor(R.color.colorPrimary));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

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

    @OnClick({R.id.tv_score, R.id.tv_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_score:
                vScore.setVisibility(View.VISIBLE);
                vRecord.setVisibility(View.GONE);
                break;
            case R.id.tv_record:
                vScore.setVisibility(View.GONE);
                vRecord.setVisibility(View.VISIBLE);
                break;
        }
    }
}
