package cn.bluemobi.dylan.step.activity;

import android.os.Bundle;
import android.app.Activity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.bluemobi.dylan.step.R;
import cn.bluemobi.dylan.step.step.bean.StepData;
import cn.bluemobi.dylan.step.step.utils.ChartUtils;
import cn.bluemobi.dylan.step.step.utils.DbUtils;

public class VisualActivity extends Activity {
    private LineChart chart;

    private void assignViews() {
        chart = (LineChart) findViewById(R.id.chart);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual);
        assignViews();
        initData();
    }

    private void initData() {
        ChartUtils.initChart(chart);
        ChartUtils.notifyDataSetChanged(chart, getData(), ChartUtils.weekValue);
    }


    private List<Entry> getData() {
        List<Entry> values = new ArrayList<>();
        List<StepData> stepDatas = DbUtils.getQueryAll(StepData.class);
        // 真实数据
        // Integer i = 0;
        // for(StepData item : stepDatas) {
        //     values.add(new Entry(i, Float.parseFloat(item.getStep())));
        //     i = i + 1;
        // }
        // Logger.d("teststepDatas"+stepDatas.size());
        // for(int x = i; x <= 6; x = x+1) {
        //     values.add(new Entry(x, 100));
        // }

        // 测试数据
        values.add(new Entry(0, 1500));
        values.add(new Entry(1, 2000));
        values.add(new Entry(2, 2345));
        values.add(new Entry(3, 2000));
        values.add(new Entry(4, 1000));
        values.add(new Entry(5, 3500));
        values.add(new Entry(6, 10000));
        return values;
    }
}
