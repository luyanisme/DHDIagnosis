package com.example.luyan.dhdiagnosis.UI.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luyan.dhdiagnosis.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaveChartFragment extends Fragment {

    private BarChart mChart;
    private Context mcontext;
    private List<BarEntry> mSinusData;

    public WaveChartFragment(Context context) {
        // Required empty public constructor
        this.mcontext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wave_chart, container, false);

        mSinusData = FileUtils.loadBarEntriesFromAssets(mcontext.getAssets(), "othersine.txt");

        mChart = (BarChart) view.findViewById(R.id.wave_chart);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        leftAxis.setAxisMinValue(-2.5f);
        leftAxis.setAxisMaxValue(2.5f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(6, false);
        rightAxis.setAxisMinValue(-2.5f);
        rightAxis.setAxisMaxValue(2.5f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        mChart.animateXY(2000, 2000);
        setData(200);
        return view;
    }

    private void setData(int count) {

        ArrayList<String> xVals = new ArrayList<String>();

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            xVals.add(i + "");
            entries.add(mSinusData.get(i));
        }

        BarDataSet set = new BarDataSet(entries, "Sinus Function");
        set.setBarSpacePercent(40f);
        set.setColor(Color.rgb(240, 120, 124));

        BarData data = new BarData(xVals, set);
        data.setValueTextSize(10f);
        data.setDrawValues(false);

        mChart.setData(data);
    }

}
